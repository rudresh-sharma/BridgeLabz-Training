package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** DAO for the room table. */
public class RoomDAO {

    public int insert(Room r) throws SQLException {
        String sql = "INSERT INTO room(room_number,room_type,department_id,capacity,is_available,daily_rate) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1,r.getRoomNumber()); ps.setString(2,r.getRoomType());
            if (r.getDepartmentId()!=null) ps.setInt(3,r.getDepartmentId()); else ps.setNull(3,Types.INTEGER);
            ps.setInt(4,r.getCapacity()); ps.setBoolean(5,r.isAvailable()); ps.setDouble(6,r.getDailyRate());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) { return keys.next()?keys.getInt(1):-1; }
        }
    }

    public Room findById(int id) throws SQLException {
        String sql = "SELECT r.*, d.name AS department_name FROM room r LEFT JOIN department d ON r.department_id=d.department_id WHERE r.room_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next()?map(rs):null; }
        }
    }

    public List<Room> findAvailable() throws SQLException {
        String sql = "SELECT r.*, d.name AS department_name FROM room r LEFT JOIN department d ON r.department_id=d.department_id WHERE r.is_available=TRUE ORDER BY r.room_type, r.room_number";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { return mapList(rs); }
    }

    public List<Room> findAll() throws SQLException {
        String sql = "SELECT r.*, d.name AS department_name FROM room r LEFT JOIN department d ON r.department_id=d.department_id ORDER BY r.room_number";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { return mapList(rs); }
    }

    public List<Room> findByType(String type) throws SQLException {
        String sql = "SELECT r.*, d.name AS department_name FROM room r LEFT JOIN department d ON r.department_id=d.department_id WHERE r.room_type=? ORDER BY r.room_number";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,type);
            try (ResultSet rs = ps.executeQuery()) { return mapList(rs); }
        }
    }

    public boolean setAvailability(int roomId, boolean available) throws SQLException {
        String sql = "UPDATE room SET is_available=? WHERE room_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1,available); ps.setInt(2,roomId);
            return ps.executeUpdate()>0;
        }
    }

    public boolean update(Room r) throws SQLException {
        String sql = "UPDATE room SET room_number=?,room_type=?,capacity=?,daily_rate=? WHERE room_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,r.getRoomNumber()); ps.setString(2,r.getRoomType());
            ps.setInt(3,r.getCapacity()); ps.setDouble(4,r.getDailyRate()); ps.setInt(5,r.getRoomId());
            return ps.executeUpdate()>0;
        }
    }

    public ResultSet getOccupancySummary(Connection conn) throws SQLException {
        String sql = """
            SELECT room_type,
                   COUNT(*) AS total_rooms,
                   SUM(is_available=FALSE) AS occupied,
                   SUM(is_available=TRUE) AS available,
                   ROUND(SUM(is_available=FALSE)*100.0/COUNT(*),2) AS occupancy_pct
            FROM room GROUP BY room_type ORDER BY occupancy_pct DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    private List<Room> mapList(ResultSet rs) throws SQLException {
        List<Room> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Room map(ResultSet rs) throws SQLException {
        Room r = new Room();
        r.setRoomId    (rs.getInt    ("room_id"));
        r.setRoomNumber(rs.getString ("room_number"));
        r.setRoomType  (rs.getString ("room_type"));
        int did = rs.getInt("department_id"); r.setDepartmentId(rs.wasNull()?null:did);
        r.setCapacity  (rs.getInt    ("capacity"));
        r.setAvailable (rs.getBoolean("is_available"));
        r.setDailyRate (rs.getDouble ("daily_rate"));
        try { r.setDepartmentName(rs.getString("department_name")); } catch (SQLException ignored) {}
        return r;
    }
}

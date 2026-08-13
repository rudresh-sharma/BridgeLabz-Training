package com.employeepayroll.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table("employee")
public class Employee implements Persistable<UUID> {

    @Id
    @Column("id")
    private UUID id;

    @Column("name")
    private String name;

    @Column("email")
    private String email;

    @Column("phone")
    private String phone;

    @Column("salary")
    private BigDecimal salary;

    @Column("department_id")
    private UUID departmentId;

    @Transient
    private boolean isNew = true;

    // Used by Spring Data JDBC when it loads a row FROM the database.
    // Marks the entity as "not new" since it already exists.
    @PersistenceCreator
    public Employee(UUID id, String name, String email, String phone,
                     BigDecimal salary, UUID departmentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.departmentId = departmentId;
        this.isNew = false;
    }

    // Used by YOUR code when creating a brand-new employee.
    public Employee() {
        this.isNew = true;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }
}
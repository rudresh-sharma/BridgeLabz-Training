package com.employeepayroll.entity;

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
@Table("department")
public class Department implements Persistable<UUID> {

    @Id
    @Column("id")
    private UUID id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Transient
    private boolean isNew = true;

    // Used by Spring Data JDBC when loading a row FROM the database
    @PersistenceCreator
    public Department(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isNew = false;
    }

    // Used by YOUR code when creating a brand-new department
    public Department() {
        this.isNew = true;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }
}
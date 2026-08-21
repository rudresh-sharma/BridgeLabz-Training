package com.fundoonotesapp.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundoonotesapp.audit.entity.AuditLog;

public interface AuditLogRepository
        extends JpaRepository<AuditLog, Long> {
}
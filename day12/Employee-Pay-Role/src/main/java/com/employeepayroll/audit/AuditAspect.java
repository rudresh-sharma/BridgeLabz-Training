package com.employeepayroll.audit;

import java.time.Instant;
import java.util.UUID;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogRepository auditLogRepository;

    @AfterReturning(
        pointcut = "execution(* com.employeepayroll.service.employee.impl.EmployeeServiceImpl.createEmployee(..)) || "
                 + "execution(* com.employeepayroll.service.employee.impl.EmployeeServiceImpl.updateEmployee(..)) || "
                 + "execution(* com.employeepayroll.service.employee.impl.EmployeeServiceImpl.deleteEmployee(..))",
        returning = "result"
    )
    public void auditEmployeeChange(JoinPoint joinPoint, Object result) {
        saveAudit("EMPLOYEE_" + joinPoint.getSignature().getName().toUpperCase(),
                "Employee", result);
    }

    @AfterReturning(
        pointcut = "execution(* com.employeepayroll.service.department.DepartmentServiceImpl.createDepartment(..)) || "
                 + "execution(* com.employeepayroll.service.department.DepartmentServiceImpl.updateDepartment(..)) || "
                 + "execution(* com.employeepayroll.service.department.DepartmentServiceImpl.deleteDepartment(..))",
        returning = "result"
    )
    public void auditDepartmentChange(JoinPoint joinPoint, Object result) {
        saveAudit("DEPARTMENT_" + joinPoint.getSignature().getName().toUpperCase(),
                "Department", result);
    }

    private void saveAudit(String action, String entityType, Object result) {
        AuditLog audit = new AuditLog();
        audit.setAction(action);
        audit.setEntityType(entityType);
        audit.setEntityId(extractId(result));   // just the UUID, not the whole object
        audit.setDetails(action + " performed successfully");
        audit.setTimestamp(Instant.now());
        auditLogRepository.save(audit);
    }

    private String extractId(Object result) {
        if (result == null) return "N/A";
        try {
            var idField = result.getClass().getMethod("getId");
            Object id = idField.invoke(result);
            return id != null ? id.toString() : "N/A";
        } catch (Exception e) {
            return "N/A";
        }
    }
}
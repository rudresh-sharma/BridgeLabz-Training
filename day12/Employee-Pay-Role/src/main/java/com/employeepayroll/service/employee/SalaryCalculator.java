package com.employeepayroll.service.employee;

import com.employeepayroll.entity.Employee;
import java.math.BigDecimal;

public interface SalaryCalculator {
    BigDecimal calculateAnnualSalary(Employee employee);
}
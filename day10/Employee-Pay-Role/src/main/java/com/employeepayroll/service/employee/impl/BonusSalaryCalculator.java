package com.employeepayroll.service.employee.impl;

import com.employeepayroll.entity.Employee;
import com.employeepayroll.service.employee.SalaryCalculator;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service("bonusSalaryCalculator")
public class BonusSalaryCalculator implements SalaryCalculator {

    @Override
    public BigDecimal calculateAnnualSalary(Employee employee) {
        return employee.getSalary()
                .multiply(BigDecimal.valueOf(12))
                .multiply(BigDecimal.valueOf(1.10));
    }
}
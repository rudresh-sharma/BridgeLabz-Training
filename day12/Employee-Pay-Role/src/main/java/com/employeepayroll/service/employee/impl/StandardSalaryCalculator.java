package com.employeepayroll.service.employee.impl;

import com.employeepayroll.entity.Employee;
import com.employeepayroll.service.employee.SalaryCalculator;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Primary
@Service("standardSalaryCalculator")
public class StandardSalaryCalculator implements SalaryCalculator {

    @Override
    public BigDecimal calculateAnnualSalary(Employee employee) {
        return employee.getSalary().multiply(BigDecimal.valueOf(12));
    }
}
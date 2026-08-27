package com.fundoo.notesservice.logging;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect {

    @Around("execution(* com.fundoo.notesservice..controller..*(..))")
    public Object logControllerExecution(
            ProceedingJoinPoint joinPoint
    ) throws Throwable {

        String className =
                joinPoint.getTarget()
                        .getClass()
                        .getSimpleName();

        String methodName =
                joinPoint.getSignature()
                        .getName();

        long startTime = System.currentTimeMillis();

        log.info(
                "REQUEST STARTED: {}.{}()",
                className,
                methodName
        );

        try {

            Object result = joinPoint.proceed();

            long executionTime =
                    System.currentTimeMillis() - startTime;

            log.info(
                    "REQUEST COMPLETED: {}.{}() | {} ms",
                    className,
                    methodName,
                    executionTime
            );

            return result;

        } catch (Exception exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            log.error(
                    "REQUEST FAILED: {}.{}() | {} ms | Error: {}",
                    className,
                    methodName,
                    executionTime,
                    exception.getMessage()
            );

            throw exception;
        }
    }
}

package com.example.manticore.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

@Aspect
public class GetTransactionIsolationAspect {
    Logger logger = LoggerFactory.getLogger(GetTransactionIsolationAspect.class);

    @Around("execution(* com.mysql.cj.jdbc.ConnectionImpl.getTransactionIsolation())")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        // Custom logic before the method execution
        logger.info("Before method execution");

        // Proceed with the actual method execution
        Object result = null ;
        try {
            result = joinPoint.proceed();
        } catch (SQLException e) {
            logger.info("The exception captured:", e);
            logger.warn("'READ-COMMITTED' is returned instead as the default transaction isolation level.");
            return Connection.TRANSACTION_READ_COMMITTED;
        }

        // Custom logic after the method execution
        logger.info("After method execution");

        return result;
    }
}

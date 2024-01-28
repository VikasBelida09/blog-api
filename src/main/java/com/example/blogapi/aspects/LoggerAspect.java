package com.example.blogapi.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
    @Around("execution(* com.example.blogapi.services..*(..))") // Target all methods within services package
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        // Log the method, arguments, and execution time
        logger.info("Method {} executed in {}ms with arguments {}",
                joinPoint.getSignature().getName(), timeTaken, Arrays.toString(joinPoint.getArgs()));
        return proceed;
    }

}

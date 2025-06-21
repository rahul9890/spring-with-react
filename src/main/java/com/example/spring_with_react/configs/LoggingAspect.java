package com.example.spring_with_react.configs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.spring_with_react..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String fullClassName = joinPoint.getSignature().getDeclaringTypeName();

        // Extract just the simple class name (without package)
        String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

        logger.info("In class {} Logging before execution of method: {}", className, methodName);
    }

}

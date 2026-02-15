package com.sathwikhbhat.apiexecutiontracker.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ExecutionTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    @Around("@within(com.sathwikhbhat.apiexecutiontracker.annotation.TrackExecutionTime) || " +
            "@annotation(com.sathwikhbhat.apiexecutiontracker.annotation.TrackExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long end = System.nanoTime();
            long durationMs = (end - start) / 1_000_000;

            if (logger.isInfoEnabled()) {
                logger.info("{} executed in {} ms", joinPoint.getSignature().toShortString(), durationMs);
            }
        }
    }
}

package com.sathwikhbhat.apiexecutiontracker.aspect;

import com.sathwikhbhat.apiexecutiontracker.config.TrackerProperties;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ExecutionTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);
    private final TrackerProperties properties;

    public ExecutionTimeAspect(TrackerProperties properties) {
        this.properties = properties;
    }


    @Around("@within(com.sathwikhbhat.apiexecutiontracker.annotation.TrackExecutionTime) || " +
            "@annotation(com.sathwikhbhat.apiexecutiontracker.annotation.TrackExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long end = System.nanoTime();
            long durationNs = end - start;
            long duration = convert(durationNs);

            if (logger.isInfoEnabled() && duration >= properties.getThreshold()) {
                logger.info(
                        "{} executed in {} {}",
                        joinPoint.getSignature().toShortString(),
                        duration,
                        properties.getTimeUnit().name().toLowerCase()
                );
            }
        }
    }

    private long convert(long nanos) {
        return switch (properties.getTimeUnit()) {
            case SECONDS -> nanos / 1_000_000_000;
            case MILLISECONDS -> nanos / 1_000_000;
            case MICROSECONDS -> nanos / 1_000;
            case NANOSECONDS -> nanos;
        };
    }

}

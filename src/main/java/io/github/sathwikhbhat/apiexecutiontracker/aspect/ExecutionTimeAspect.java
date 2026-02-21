package io.github.sathwikhbhat.apiexecutiontracker.aspect;

import io.github.sathwikhbhat.apiexecutiontracker.config.TrackerProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Aspect that measures and logs execution duration for members annotated with
 * {@code @TrackExecutionTime}.
 */
@Aspect
public class ExecutionTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);
    private final TrackerProperties properties;

    public ExecutionTimeAspect(TrackerProperties properties) {
        this.properties = properties;
    }

    /**
     * Intercepts methods directly annotated with {@code @TrackExecutionTime},
     * as well as methods declared in types annotated with it.
     *
     * @param joinPoint intercepted join point
     * @return intercepted method result
     * @throws Throwable propagated exception from intercepted method
     */
    @Around("@within(io.github.sathwikhbhat.apiexecutiontracker.annotation.TrackExecutionTime) || " +
            "@annotation(io.github.sathwikhbhat.apiexecutiontracker.annotation.TrackExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long end = System.nanoTime();
            long durationNs = end - start;
            long duration = convert(durationNs);

            if (logger.isInfoEnabled() && duration >= properties.getThreshold()) {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

                if (requestAttributes != null) {
                    // Prefer HTTP context details for controller endpoints.
                    HttpServletRequest request = requestAttributes.getRequest();

                    String method = request.getMethod();
                    String path = request.getRequestURI();

                    String query = request.getQueryString();
                    String fullPath = (query == null) ? path : path + "?" + query;

                    logger.info(
                            "{} {} executed in {} {}",
                            method,
                            fullPath,
                            duration,
                            properties.getTimeUnit().name().toLowerCase()
                    );
                } else {
                    // Fallback for non-web contexts where request attributes are unavailable.
                    logger.info(
                            "{} executed in {} {}",
                            joinPoint.getSignature().toShortString(),
                            duration,
                            properties.getTimeUnit().name().toLowerCase()
                    );
                }
            }
        }
    }

    /**
     * Converts a duration from nanoseconds into the configured output unit.
     *
     * @param nanos execution time in nanoseconds
     * @return converted duration in the configured {@code TimeUnitType}
     */
    private long convert(long nanos) {
        return switch (properties.getTimeUnit()) {
            case SECONDS -> nanos / 1_000_000_000;
            case MILLISECONDS -> nanos / 1_000_000;
            case MICROSECONDS -> nanos / 1_000;
            case NANOSECONDS -> nanos;
        };
    }

}

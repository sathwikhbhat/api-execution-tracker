package io.github.sathwikhbhat.apiexecutiontracker.annotation;

import java.lang.annotation.*;

/**
 * Marks a class or method for execution-time tracking.
 * <p>
 * When this annotation is present, {@code ExecutionTimeAspect} measures the
 * execution duration and logs it based on configured tracker properties.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrackExecutionTime {
}

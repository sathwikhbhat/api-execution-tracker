package io.github.sathwikhbhat.apiexecutiontracker.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TrackExecutionTime {
}

package com.sathwikhbhat.apiexecutiontracker.autoconfig;

import com.sathwikhbhat.apiexecutiontracker.aspect.ExecutionTimeAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutionTrackerAutoConfiguration {

    @Bean
    public ExecutionTimeAspect executionTimeAspect() {
        return new ExecutionTimeAspect();
    }

}

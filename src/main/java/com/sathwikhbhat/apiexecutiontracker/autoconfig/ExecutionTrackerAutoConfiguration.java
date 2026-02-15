package com.sathwikhbhat.apiexecutiontracker.autoconfig;

import com.sathwikhbhat.apiexecutiontracker.aspect.ExecutionTimeAspect;
import com.sathwikhbhat.apiexecutiontracker.config.TrackerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TrackerProperties.class)
@ConditionalOnProperty(prefix = "tracker", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ExecutionTrackerAutoConfiguration {

    @Bean
    public ExecutionTimeAspect executionTimeAspect(TrackerProperties properties) {
        return new ExecutionTimeAspect(properties);
    }

}

package io.github.sathwikhbhat.apiexecutiontracker.autoconfig;

import io.github.sathwikhbhat.apiexecutiontracker.aspect.ExecutionTimeAspect;
import io.github.sathwikhbhat.apiexecutiontracker.config.TrackerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Autoconfiguration that registers execution tracking components when enabled.
 */
@Configuration
@EnableConfigurationProperties(TrackerProperties.class)
@ConditionalOnProperty(prefix = "tracker", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ExecutionTrackerAutoConfiguration {

    /**
     * Creates the AOP aspect responsible for execution-time logging.
     *
     * @param properties bound tracker configuration properties
     * @return execution-time aspect bean
     */
    @Bean
    public ExecutionTimeAspect executionTimeAspect(TrackerProperties properties) {
        return new ExecutionTimeAspect(properties);
    }

}

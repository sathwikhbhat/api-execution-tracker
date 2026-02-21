package io.github.sathwikhbhat.apiexecutiontracker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for API execution tracking.
 */
@ConfigurationProperties(prefix = "tracker")
public class TrackerProperties {

    /**
     * Creates tracker properties with default values.
     */
    public TrackerProperties() {
    }

    /**
     * Enables or disables the tracker autoconfiguration.
     */
    private boolean enabled = true;
    /**
     * Time unit used for duration conversion and threshold comparison.
     */
    private TimeUnitType timeUnit = TimeUnitType.MILLISECONDS;
    /**
     * Minimum duration required to emit a log entry, expressed in {@code timeUnit}.
     */
    private long threshold = 0;

    /**
     * Returns whether the tracker autoconfiguration is enabled.
     *
     * @return {@code true} if enabled, {@code false} otherwise
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets whether the tracker autoconfiguration is enabled.
     *
     * @param enabled {@code true} to enable, {@code false} to disable
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns the configured time unit for duration conversion and threshold comparison.
     *
     * @return the configured {@link TimeUnitType}
     */
    public TimeUnitType getTimeUnit() {
        return timeUnit;
    }

    /**
     * Sets the time unit for duration conversion and threshold comparison.
     *
     * @param timeUnit the {@link TimeUnitType} to use
     */
    public void setTimeUnit(TimeUnitType timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * Returns the minimum duration required to emit a log entry, expressed in the configured time unit.
     *
     * @return threshold duration
     */
    public long getThreshold() {
        return threshold;
    }

    /**
     * Sets the minimum duration required to emit a log entry, expressed in the configured time unit.
     *
     * @param threshold threshold duration
     */
    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }
}

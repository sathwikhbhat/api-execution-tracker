package io.github.sathwikhbhat.apiexecutiontracker.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tracker")
public class TrackerProperties {

    private boolean enabled = true;
    private TimeUnitType timeUnit = TimeUnitType.MILLISECONDS;
    private long threshold = 0;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public TimeUnitType getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnitType timeUnit) {
        this.timeUnit = timeUnit;
    }

    public long getThreshold() {
        return threshold;
    }

    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }
}

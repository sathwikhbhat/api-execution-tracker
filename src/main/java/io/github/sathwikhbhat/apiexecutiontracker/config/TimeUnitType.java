package io.github.sathwikhbhat.apiexecutiontracker.config;

/**
 * Supported output units for execution-time logging.
 * <p>
 * This enum defines the available time units for converting and logging execution duration.
 * <ul>
 *   <li>{@link #SECONDS} - Duration in seconds (1 second = 1,000,000,000 nanoseconds)</li>
 *   <li>{@link #MILLISECONDS} - Duration in milliseconds (1 ms = 1,000,000 nanoseconds)</li>
 *   <li>{@link #MICROSECONDS} - Duration in microseconds (1 μs = 1,000 nanoseconds)</li>
 *   <li>{@link #NANOSECONDS} - Duration in nanoseconds (base unit)</li>
 * </ul>
 */
public enum TimeUnitType {
    /**
     * Duration in seconds.
     */
    SECONDS,
    /**
     * Duration in milliseconds.
     */
    MILLISECONDS,
    /**
     * Duration in microseconds.
     */
    MICROSECONDS,
    /**
     * Duration in nanoseconds.
     */
    NANOSECONDS
}

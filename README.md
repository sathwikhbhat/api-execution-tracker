# API Execution Tracker

[![Java 21+](https://img.shields.io/badge/Java-21%2B-E76F00)](#environment)
[![Spring Boot 4+](https://img.shields.io/badge/Spring%20Boot-4%2B-2E7D32)](#compatibility)
[![Maven](https://img.shields.io/badge/Build-Maven-C71A36)](#build--verify)
[![License: MIT](https://img.shields.io/badge/License-MIT-3B82F6)](LICENSE)

Measure and log API execution time in Spring Boot with one annotation, automatic setup, and configurable thresholds.

> [!WARNING]
> This dependency supports **Spring Boot 4+ only**.
> It is **not compatible with Spring Boot 3.x**.

## Why Use It
- Track execution time using one annotation: `@TrackExecutionTime`
- Web context logging: HTTP method + path + query string
- Endpoint-level logging for request-driven flows
- Configurable output unit and log threshold
- Autoconfiguration when dependency is present

## Installation
Add this dependency in your application `pom.xml`:

```xml
<dependency>
    <groupId>io.github.sathwikhbhat</groupId>
    <artifactId>api-execution-tracker</artifactId>
    <version>1.0.0</version>
</dependency>
```

Official dependency page:
[Maven Central Repository: API Execution Tracker](https://central.sonatype.com/artifact/io.github.sathwikhbhat/api-execution-tracker)

## Usage
Annotate a class or specific methods:

```java
import io.github.sathwikhbhat.apiexecutiontracker.annotation.TrackExecutionTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@TrackExecutionTime
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
    
}
```

## Configuration
Property prefix: `tracker`

```yaml
tracker:
  enabled: true
  time-unit: milliseconds
  threshold: 0
```

| Property | Type | Default | Description |
|---|---|---|---|
| `tracker.enabled` | `boolean` | `true` | Enables/disables tracking |
| `tracker.time-unit` | `seconds` \| `milliseconds` \| `microseconds` \| `nanoseconds` | `milliseconds` | Output time unit |
| `tracker.threshold` | `long` | `0` | Minimum duration to log, interpreted in `tracker.time-unit` |

> [!IMPORTANT]
> `tracker.threshold` always uses the same unit as `tracker.time-unit`.<br>
> By default, `tracker.time-unit` is `milliseconds`, so the threshold is in milliseconds.<br>
> If you set `tracker.time-unit` to `seconds`, the threshold will be read in seconds.

## Example Logs
When called during an HTTP request:

```text
GET /api/v1/users executed in 12 milliseconds
```

## Important Behavior
- Logging output is request-context driven.
- If an annotated method runs during an active HTTP request (including service methods called by controllers), logs show the endpoint path (for example, `GET /api/v1/xyz`) instead of the Java method signature.
- In practice for request-driven usage, expect endpoint-based logs rather than per-method signature logs.
- Execution time is logged even when the request flow throws an error/exception.

## Development / Contributing
For internals and contribution workflow, see [DEVELOPER_README](DEVELOPER_README.md).

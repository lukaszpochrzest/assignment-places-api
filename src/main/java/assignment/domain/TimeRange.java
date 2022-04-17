package assignment.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalTime;

@Value
@Builder
public class TimeRange {
    @NonNull LocalTime start;
    @NonNull LocalTime end;
}

package assignment.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.DayOfWeek;
import java.util.List;

@Value
@Builder
public class OpeningHoursEntry {
    @NonNull Type type;
    @NonNull DayOfWeek start;
    DayOfWeek end;
    @NonNull List<TimeRange> timeRanges;
}

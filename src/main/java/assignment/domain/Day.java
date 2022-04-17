package assignment.domain;

import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class Day {
    @NonNull Type type;
    @NonNull List<TimeRange> timeRanges;
}

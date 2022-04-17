package assignment.domain;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Place {
    String displayedWhat;
    String displayedWhere;
    List<OpeningHoursEntry> openingHours;
}

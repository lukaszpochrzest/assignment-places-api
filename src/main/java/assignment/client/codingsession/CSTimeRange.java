package assignment.client.codingsession;

import lombok.*;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CSTimeRange {
    LocalTime start;
    LocalTime end;
    String type;
}

package assignment.client.codingsession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CSWeek {
    private List<CSTimeRange> monday;
    private List<CSTimeRange> tuesday;
    private List<CSTimeRange> wednesday;
    private List<CSTimeRange> thursday;
    private List<CSTimeRange> friday;
    private List<CSTimeRange> saturday;
    private List<CSTimeRange> sunday;
}

package assignment;

import assignment.client.codingsession.CSOpeningHours;
import assignment.client.codingsession.CSTimeRange;
import assignment.client.codingsession.ICSApiClient;
import assignment.domain.Day;
import assignment.domain.OpeningHoursEntry;
import assignment.domain.Place;
import assignment.domain.TimeRange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static assignment.domain.Type.CLOSED;
import static assignment.domain.Type.OPEN;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final ICSApiClient client;

    public Place getPlace(String id) {
        var csPlace = client.getPlace(id);
        if (csPlace == null) {
            return null;
        }
        var openingHours = buildOpeningHours(csPlace.getOpeningHours());
        return Place.builder()
                .displayedWhat(csPlace.getDisplayedWhat())
                .displayedWhere(csPlace.getDisplayedWhere())
                .openingHours(openingHours)
                .build();
    }

    private List<OpeningHoursEntry> buildOpeningHours(CSOpeningHours csOpeningHours) {
        if (csOpeningHours == null || csOpeningHours.getDays() == null) {
            return alwaysClosed();
        }
        var csDays = csOpeningHours.getDays();
        Day[] days = new Day[7];
        days[0] = convertToDay(csDays.getMonday());
        days[1] = convertToDay(csDays.getTuesday());
        days[2] = convertToDay(csDays.getWednesday());
        days[3] = convertToDay(csDays.getThursday());
        days[4] = convertToDay(csDays.getFriday());
        days[5] = convertToDay(csDays.getSaturday());
        days[6] = convertToDay(csDays.getSunday());

        var openingHours = new ArrayList<OpeningHoursEntry>();
        for (int dayI = 0; dayI < days.length; dayI++) {
            var entry = buildOpeningHoursEntry(dayI, days);
            openingHours.add(entry);
            dayI = entry.getEnd() != null ? entry.getEnd().getValue() - 1 : dayI;
        }
        return openingHours;
    }

    private OpeningHoursEntry buildOpeningHoursEntry(int startDayI, Day[] days) {
        var endDayI = findLastEqual(startDayI, days);
        return OpeningHoursEntry.builder()
                .type(days[startDayI].getType())
                .start(DayOfWeek.of(startDayI + 1))
                .end(startDayI == endDayI ? null : DayOfWeek.of(endDayI + 1))
                .timeRanges(days[startDayI].getTimeRanges())
                .build();
    }

    private int findLastEqual(int startDayI, Day[] days) {
        var result = startDayI;
        for (int i = startDayI + 1; i < days.length; i++) {
            if (days[startDayI].equals(days[i])) {
                result = i;
            } else {
                break;
            }
        }
        return result;
    }

    private Day convertToDay(List<CSTimeRange> timeRanges) {
        if (timeRanges == null || timeRanges.isEmpty()) {
            return new Day(CLOSED, Collections.emptyList());
        }
        return new Day(OPEN, convertToTimeRange(timeRanges));
    }

    private List<TimeRange> convertToTimeRange(List<CSTimeRange> timeRanges) {
        return timeRanges.stream()
                .filter(tr -> "OPEN".equals(tr.getType()))
                .map(tr -> TimeRange.builder().start(tr.getStart()).end(tr.getEnd()).build())
                .sorted(Comparator.comparing(TimeRange::getStart))
                .collect(Collectors.toList());
    }

    private List<OpeningHoursEntry> alwaysClosed() {
        return List.of(OpeningHoursEntry.builder().type(CLOSED).start(DayOfWeek.MONDAY).end(DayOfWeek.SUNDAY).timeRanges(List.of()).build());
    }
}

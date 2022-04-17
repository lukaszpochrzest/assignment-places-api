package assignment;

import assignment.client.codingsession.CSOpeningHours;
import assignment.client.codingsession.CSPlace;
import assignment.client.codingsession.CSTimeRange;
import assignment.client.codingsession.CSWeek;
import assignment.domain.OpeningHoursEntry;
import assignment.domain.TimeRange;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalTime;
import java.util.List;

import static assignment.domain.Type.CLOSED;
import static assignment.domain.Type.OPEN;
import static java.time.DayOfWeek.*;

public class PlaceServiceTestCases {
    public static Arguments case_ohGSnJtMIC5nPfYRi_HTAg() {
        return Arguments.of(buildCSApiResponse(CSWeek.builder()
                        .tuesday(List.of(apiTimeRange("11:30", "15:00"), apiTimeRange("18:30", "00:00")))
                        .wednesday(List.of(apiTimeRange("11:30", "15:00"), apiTimeRange("18:30", "00:00")))
                        .thursday(List.of(apiTimeRange("11:30", "15:00"), apiTimeRange("18:30", "00:00")))
                        .friday(List.of(apiTimeRange("11:30", "15:00"), apiTimeRange("18:30", "00:00")))
                        .saturday(List.of(apiTimeRange("18:30", "00:00")))
                        .sunday(List.of(apiTimeRange("11:30", "15:00")))
                        .build()),
                List.of(
                        OpeningHoursEntry.builder()
                                .start(MONDAY)
                                .type(CLOSED)
                                .timeRanges(List.of())
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(TUESDAY).end(FRIDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("11:30", "15:00"), timeRange("18:30", "00:00")))
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(SATURDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("18:30", "00:00")))
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(SUNDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("11:30", "15:00")))
                                .build()
                )
        );
    }

    public static Arguments case_GXvPAor1ifNfpF0U5PTG0w() {
        return Arguments.of(buildCSApiResponse(CSWeek.builder()
                        .monday(List.of(apiTimeRange("11:30", "14:00"), apiTimeRange("18:30", "22:00")))
                        .tuesday(List.of(apiTimeRange("11:30", "14:00"), apiTimeRange("18:30", "22:00")))
                        .wednesday(List.of(apiTimeRange("11:30", "14:00"), apiTimeRange("18:30", "22:00")))
                        .thursday(List.of(apiTimeRange("11:30", "14:00"), apiTimeRange("18:30", "22:00")))
                        .friday(List.of(apiTimeRange("11:30", "14:00"), apiTimeRange("18:30", "22:00")))
                        .build()),
                List.of(
                        OpeningHoursEntry.builder()
                                .start(MONDAY)
                                .end(FRIDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("11:30", "14:00"), timeRange("18:30", "22:00")))
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(SATURDAY)
                                .end(SUNDAY)
                                .type(CLOSED)
                                .timeRanges(List.of())
                                .build()
                )
        );
    }


    public static Arguments case_3() {
        return Arguments.of(buildCSApiResponse(CSWeek.builder()
                        .tuesday(List.of(apiTimeRange("11:30", "14:00")))
                        .wednesday(List.of(apiTimeRange("11:31", "14:00")))
                        .build()),
                List.of(
                        OpeningHoursEntry.builder()
                                .start(MONDAY)
                                .type(CLOSED)
                                .timeRanges(List.of())
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(TUESDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("11:30", "14:00")))
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(WEDNESDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("11:31", "14:00")))
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(THURSDAY)
                                .end(SUNDAY)
                                .type(CLOSED)
                                .timeRanges(List.of())
                                .build()
                )
        );
    }

    public static Arguments case_4() {
        return Arguments.of(buildCSApiResponse(CSWeek.builder()
                        .tuesday(List.of(apiTimeRange("11:30", "14:00")))
                        .wednesday(List.of(apiTimeRange("11:30", "14:01")))
                        .build()),
                List.of(
                        OpeningHoursEntry.builder()
                                .start(MONDAY)
                                .type(CLOSED)
                                .timeRanges(List.of())
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(TUESDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("11:30", "14:00")))
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(WEDNESDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("11:30", "14:01")))
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(THURSDAY)
                                .end(SUNDAY)
                                .type(CLOSED)
                                .timeRanges(List.of())
                                .build()
                )
        );
    }

    public static Arguments case_5() {
        return Arguments.of(buildCSApiResponse(CSWeek.builder()
                        .sunday(List.of(apiTimeRange("12:00", "12:30")))
                        .build()),
                List.of(
                        OpeningHoursEntry.builder()
                                .start(MONDAY)
                                .end(SATURDAY)
                                .type(CLOSED)
                                .timeRanges(List.of())
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(SUNDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("12:00", "12:30")))
                                .build()
                )
        );
    }

    public static Arguments case_6() {
        return Arguments.of(buildCSApiResponse(CSWeek.builder()
                        .saturday(List.of(apiTimeRange("13:00", "13:30"), apiTimeRange("12:00", "12:30")))
                        .sunday(List.of(apiTimeRange("12:00", "12:30"), apiTimeRange("13:00", "13:30")))
                        .build()),
                List.of(
                        OpeningHoursEntry.builder()
                                .start(MONDAY)
                                .end(FRIDAY)
                                .type(CLOSED)
                                .timeRanges(List.of())
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(SATURDAY)
                                .end(SUNDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("12:00", "12:30"), timeRange("13:00", "13:30")))
                                .build()
                )
        );
    }

    public static Arguments case_7() {
        return Arguments.of(buildCSApiResponse(CSWeek.builder().build()),
                List.of(OpeningHoursEntry.builder()
                        .start(MONDAY)
                        .end(SUNDAY)
                        .type(CLOSED)
                        .timeRanges(List.of())
                        .build()));
    }

    public static Arguments case_8() {
        return Arguments.of(buildCSApiResponse(CSWeek.builder()
                        .monday(List.of(apiTimeRange("09:00", "10:00")))
                        .tuesday(List.of(apiTimeRange("09:00", "10:00")))
                        .wednesday(List.of(apiTimeRange("09:00", "10:00")))
                        .thursday(List.of(apiTimeRange("09:00", "10:00"), apiTimeRange("10:30", "11:00")))
                        .friday(List.of(apiTimeRange("16:00", "17:00")))
                        .saturday(List.of(apiTimeRange("16:00", "17:00")))
                        .sunday(List.of(apiTimeRange("16:00", "17:00")))
                        .build()),
                List.of(
                        OpeningHoursEntry.builder()
                                .start(MONDAY)
                                .end(WEDNESDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("09:00", "10:00")))
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(THURSDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("09:00", "10:00"), timeRange("10:30", "11:00")))
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(FRIDAY)
                                .end(SUNDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("16:00", "17:00")))
                                .build()
                )
        );
    }

    public static Arguments case_9() {
        return Arguments.of(buildCSApiResponse(CSWeek.builder()
                        .monday(List.of(apiTimeRange("09:00", "10:00"), apiTimeRange("07:00", "08:00")))
                        .tuesday(List.of(apiTimeRange("07:00", "08:00"), apiTimeRange("09:00", "10:00")))
                        .wednesday(List.of(apiTimeRange("09:00", "10:00"), apiTimeRange("07:00", "08:00")))
                        .friday(List.of(apiTimeRange("16:00", "17:00")))
                        .saturday(List.of(apiTimeRange("16:00", "17:00")))
                        .build()),
                List.of(
                        OpeningHoursEntry.builder()
                                .start(MONDAY)
                                .end(WEDNESDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("07:00", "08:00"), timeRange("09:00", "10:00")))
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(THURSDAY)
                                .type(CLOSED)
                                .timeRanges(List.of())
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(FRIDAY)
                                .end(SATURDAY)
                                .type(OPEN)
                                .timeRanges(List.of(timeRange("16:00", "17:00")))
                                .build(),
                        OpeningHoursEntry.builder()
                                .start(SUNDAY)
                                .type(CLOSED)
                                .timeRanges(List.of())
                                .build()
                )
        );
    }

    private static CSTimeRange apiTimeRange(String start, String end) {
        return CSTimeRange.builder()
                .start(LocalTime.parse(start))
                .end(LocalTime.parse(end))
                .type("OPEN")
                .build();
    }

    private static TimeRange timeRange(String start, String end) {
        return TimeRange.builder()
                .start(LocalTime.parse(start))
                .end(LocalTime.parse(end))
                .build();
    }

    private static CSPlace buildCSApiResponse(CSWeek CSWeek) {
        var openingHours = new CSOpeningHours();
        openingHours.setDays(CSWeek);
        var response = new CSPlace();
        response.setOpeningHours(openingHours);
        return response;
    }
}

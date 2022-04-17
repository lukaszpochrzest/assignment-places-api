package assignment;

import assignment.client.codingsession.CSPlace;
import assignment.client.codingsession.ICSApiClient;
import assignment.domain.OpeningHoursEntry;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static assignment.PlaceServiceTestCases.*;

@ExtendWith(MockitoExtension.class)
public class PlaceServiceTest {

    @Mock
    private ICSApiClient mockClient;

    @InjectMocks
    private PlaceService placeService;

    private static Stream<Arguments> cases() {
        return Stream.of(
                case_GXvPAor1ifNfpF0U5PTG0w(),
                case_ohGSnJtMIC5nPfYRi_HTAg(),
                case_3(),
                case_4(),
                case_5(),
                case_6(),
                case_7(),
                case_8(),
                case_9()
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    public void groupingTest(CSPlace apiResponse, List<OpeningHoursEntry> expectedOpeningHours) {
        Mockito.when(mockClient.getPlace(Mockito.anyString())).thenReturn(apiResponse);
        var place = placeService.getPlace("any");
        Assertions.assertThat(place.getOpeningHours()).containsExactlyElementsOf(expectedOpeningHours);
    }

}

package assignment;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WireMockTest(httpPort = 8081)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "api.host=http://localhost:8081"
})
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_GXvPAor1ifNfpF0U5PTG0w() throws Exception {
        // wiremock
        stubFor(get(urlEqualTo("/coding-session-rest-api/GXvPAor1ifNfpF0U5PTG0w")).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("GXvPAor1ifNfpF0U5PTG0w.json")));

        mockMvc.perform(MockMvcRequestBuilders.get("/place/GXvPAor1ifNfpF0U5PTG0w"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.displayed_what").value("Casa Ferlin"))
                .andExpect(jsonPath("$.displayed_where").value("Stampfenbachstrasse 38, 8006 Zürich"))
                .andExpect(jsonPath("$.opening_hours", hasSize(2)))
                .andExpect(jsonPath("$.opening_hours[0].type").value("OPEN"))
                .andExpect(jsonPath("$.opening_hours[0].start").value("MONDAY"))
                .andExpect(jsonPath("$.opening_hours[0].end").value("FRIDAY"))
                .andExpect(jsonPath("$.opening_hours[0].time_ranges", hasSize(2)))
                .andExpect(jsonPath("$.opening_hours[0].time_ranges[0].start").value("11:30"))
                .andExpect(jsonPath("$.opening_hours[0].time_ranges[0].end").value("14:00"))
                .andExpect(jsonPath("$.opening_hours[0].time_ranges[1].start").value("18:30"))
                .andExpect(jsonPath("$.opening_hours[0].time_ranges[1].end").value("22:00"))
                .andExpect(jsonPath("$.opening_hours[1].type").value("CLOSED"))
                .andExpect(jsonPath("$.opening_hours[1].start").value("SATURDAY"))
                .andExpect(jsonPath("$.opening_hours[1].end").value("SUNDAY"))
                .andExpect(jsonPath("$.opening_hours[1].time_ranges", hasSize(0)));
    }

}

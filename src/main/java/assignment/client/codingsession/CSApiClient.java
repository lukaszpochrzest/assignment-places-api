package assignment.client.codingsession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
class CSApiClient implements ICSApiClient {

    private final String apiUrl;
    private final RestTemplate restTemplate;

    CSApiClient(@Value("${api.host:https://storage.googleapis.com}") String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    public CSPlace getPlace(String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        var request = new HttpEntity<>(headers);
        try {
            return restTemplate.exchange(apiUrl + "/coding-session-rest-api/{placeId}", HttpMethod.GET, request, CSPlace.class, id)
                    .getBody();
        } catch (HttpClientErrorException e) {
            if (HttpStatus.NOT_FOUND == e.getStatusCode()) {
                return null;
            }
            throw e;
        }
    }
}

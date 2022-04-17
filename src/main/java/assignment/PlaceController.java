package assignment;

import assignment.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/place/{placeId}")
    public ResponseEntity<Place> getPlace(@PathVariable String placeId) {
        var place = placeService.getPlace(placeId);
        return place != null ? ResponseEntity.ok(place) : ResponseEntity.notFound().build();
    }

}

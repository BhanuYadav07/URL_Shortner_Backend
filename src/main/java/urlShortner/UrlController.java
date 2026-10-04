package urlShortner;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urlShortner.Service.UrlService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService service;

    @PostMapping("/urls")
    public ResponseEntity<dto.UrlResponse> create(
            @Valid @RequestBody dto.CreateUrlRequest req) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.shorten(req));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {

        String longUrl = service.resolve(shortCode);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(URI.create(longUrl));

        return new ResponseEntity<>(
                headers,
                HttpStatus.FOUND
        );
    }
}
package urlShortner.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import urlShortner.Url;
import urlShortner.UrlRepository;
import urlShortner.dto;
import urlShortner.exception.UrlNotFoundException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository repo;

    @Transactional
    public dto.UrlResponse shorten(dto.CreateUrlRequest req) {

        Url url = new Url();

        url.setLongUrl(req.getLongUrl());
        url.setExpiresAt(req.getExpiresAt());

        url = repo.save(url);

        url.setShortCode(Base62Encoder.encode(url.getId()));

        repo.save(url);

        return new dto.UrlResponse(
                url.getShortCode(),
                url.getLongUrl()
        );
    }

    @Transactional
    public String resolve(String shortCode) {

        Url url = repo.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new UrlNotFoundException("URL not found")
                );

        if (url.getExpiresAt() != null &&
                url.getExpiresAt().isBefore(LocalDateTime.now())) {

            throw new UrlNotFoundException("URL has expired");
        }

        return url.getLongUrl();
    }
}
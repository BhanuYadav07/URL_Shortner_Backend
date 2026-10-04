package urlShortner;

import lombok.*;

import java.time.LocalDateTime;

public class dto {

    @Data
    @AllArgsConstructor
    @ToString @Getter @Setter
    @NoArgsConstructor
    public static class CreateUrlRequest {

        private String longUrl;
        private LocalDateTime expiresAt;
    }

    @Data
    @AllArgsConstructor
    @ToString @Getter @Setter
    @NoArgsConstructor
    public static class UrlResponse {

        private String shortCode;
        private String longUrl;
    }
}
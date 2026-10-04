package urlShortner;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import urlShortner.Service.UrlService;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    UrlService urlService;
    @Autowired
    UrlRepository urlRepository;
	@Test
    @Transactional
	void contextLoads() {
        List<String> ll = List.of("https://www.google.com/search?q=spring+boot+url+shortener",
        "https://github.com/spring-projects/spring-boot",
        "https://stackoverflow.com/questions/tagged/spring-boot",
        "https://www.youtube.com/results?search_query=java+spring+boot+tutorial",
        "https://www.amazon.in/s?k=java+spring+boot+books)");
        for(String str:ll){
           urlService.shorten(new dto.CreateUrlRequest(str,LocalDateTime.of(2026,11,1,0,0,0)));
        }
        for(Url url:urlRepository.findAll()){
            System.out.println(url);
        }
	}

}

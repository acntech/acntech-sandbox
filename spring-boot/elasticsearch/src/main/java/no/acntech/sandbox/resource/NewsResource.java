package no.acntech.sandbox.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import no.acntech.sandbox.model.News;
import no.acntech.sandbox.service.NewsService;

@RequestMapping(path = "/api/search")
@RestController
public class NewsResource {

    private final ObjectMapper objectMapper;
    private final NewsService newsService;

    public NewsResource(final ObjectMapper objectMapper,
                        final NewsService newsService) {
        this.objectMapper = objectMapper;
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<Page<News>> get(final Pageable pageable,
                                          @RequestParam(name = "type", defaultValue = "client") String type) {
        final var query = "{ \"match\": { \"category\": \"POLITICS\"  } }";
        final var news = search(type, query, pageable);
        return ResponseEntity.ok(news);
    }

    @PostMapping
    public ResponseEntity<Page<News>> post(final Pageable pageable,
                                           @RequestParam(name = "type", defaultValue = "client") String type,
                                           @RequestBody final Map<String, Object> body) throws JsonProcessingException {
        final var query = objectMapper.writeValueAsString(body);
        final var news = search(type, query, pageable);
        return ResponseEntity.ok(news);
    }

    private Page<News> search(String type, String query, final Pageable pageable) {
        if ("client".equals(type)) {
            return newsService.searchWithRestClient(query, pageable);
        } else {
            return newsService.searchWithRepository(pageable);
        }
    }
}

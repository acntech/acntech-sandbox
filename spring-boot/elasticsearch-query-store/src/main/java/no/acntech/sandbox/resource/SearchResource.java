package no.acntech.sandbox.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.model.NewsDocument;
import no.acntech.sandbox.service.NewsService;

@RequestMapping(path = "/api/search")
@RestController
public class SearchResource {

    private final NewsService newsService;

    public SearchResource(final NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<Page<NewsDocument>> get(@RequestParam("query") String queryName,
                                                  final Pageable pageable) {
        final var news = newsService.searchByQueryName(queryName, pageable);
        return ResponseEntity.ok(news);
    }
}

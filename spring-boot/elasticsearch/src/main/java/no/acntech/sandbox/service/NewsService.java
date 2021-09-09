package no.acntech.sandbox.service;

import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import no.acntech.sandbox.client.SearchElasticsearchClient;
import no.acntech.sandbox.model.NewsDocument;
import no.acntech.sandbox.repository.NewsRepository;

@Service
public class NewsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsService.class);
    private final SearchElasticsearchClient searchElasticsearchClient;
    private final NewsRepository newsRepository;

    public NewsService(final SearchElasticsearchClient searchElasticsearchClient,
                       final NewsRepository newsRepository) {
        this.searchElasticsearchClient = searchElasticsearchClient;
        this.newsRepository = newsRepository;
    }

    public Page<NewsDocument> searchWithRestClient(final String queryString, final Pageable pageable) {
        LOGGER.debug("Search for news using Elasticsearch REST client");
        final var query = new NativeSearchQueryBuilder()
                .withTrackTotalHits(true)
                .withPageable(pageable)
                .withQuery(new SimpleQueryStringBuilder(queryString))
                .build();
        return searchElasticsearchClient.search(query, pageable, NewsDocument.class);
    }

    public Page<NewsDocument> searchWithRepository(final Pageable pageable) {
        LOGGER.debug("Search for news using Elasticsearch repository");
        return newsRepository.findAll(pageable);
    }
}

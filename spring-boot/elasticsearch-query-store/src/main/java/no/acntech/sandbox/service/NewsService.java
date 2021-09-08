package no.acntech.sandbox.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

import no.acntech.sandbox.client.SearchClient;
import no.acntech.sandbox.model.NewsDocument;

@Service
public class NewsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsService.class);
    private final ObjectMapper objectMapper;
    private final SearchClient searchClient;
    private final QueryService queryService;

    public NewsService(final ObjectMapper objectMapper,
                       final SearchClient searchClient,
                       final QueryService queryService) {
        this.objectMapper = objectMapper;
        this.searchClient = searchClient;
        this.queryService = queryService;
    }

    public Page<NewsDocument> searchByQueryName(final String queryName, final Pageable pageable) {
        LOGGER.info("Search by query name {}", queryName);
        final var query = queryService.get(queryName);
        return searchByQueryMap(query.getQuery(), pageable);
    }

    public Page<NewsDocument> searchByQueryMap(final Map<String, Object> queryMap, final Pageable pageable) {
        try {
            final var queryString = objectMapper.writeValueAsString(queryMap);
            return searchByQueryString(queryString, pageable);
        } catch (IOException e) {
            LOGGER.error("Query is not correctly formatted JSON", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Query is not correctly formatted JSON", e);
        }
    }

    public Page<NewsDocument> searchByQueryString(final String queryString, final Pageable pageable) {
        LOGGER.debug("Search using query {}", queryString);
        final var query = new NativeSearchQueryBuilder()
                .withTrackTotalHits(true)
                .withPageable(pageable)
                .withQuery(new SimpleQueryStringBuilder(queryString))
                .build();
        return searchClient.search(query, pageable, NewsDocument.class);
    }
}

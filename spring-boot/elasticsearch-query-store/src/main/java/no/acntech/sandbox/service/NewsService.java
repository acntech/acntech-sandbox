package no.acntech.sandbox.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

import no.acntech.sandbox.client.SearchElasticsearchClient;
import no.acntech.sandbox.client.SearchWebClient;
import no.acntech.sandbox.model.NewsDocument;

@Service
public class NewsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsService.class);
    private final ObjectMapper objectMapper;
    private final SearchElasticsearchClient searchElasticsearchClient;
    private final SearchWebClient searchWebClient;
    private final QueryService queryService;

    public NewsService(final ObjectMapper objectMapper,
                       final SearchElasticsearchClient searchElasticsearchClient,
                       SearchWebClient searchWebClient, final QueryService queryService) {
        this.objectMapper = objectMapper;
        this.searchElasticsearchClient = searchElasticsearchClient;
        this.searchWebClient = searchWebClient;
        this.queryService = queryService;
    }

    public Page<NewsDocument> searchByQueryName(final String queryName, final Pageable pageable) {
        LOGGER.info("Search by query name {}", queryName);
        final var query = queryService.get(queryName);
        //return searchByQueryMap(query.getQuery(), pageable);
        return searchWebClient.search(query.getQuery(), pageable, NewsDocument.class);
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
        final var query = new StringQuery(queryString, pageable);
        return searchElasticsearchClient.search(query, pageable, NewsDocument.class);
    }
}

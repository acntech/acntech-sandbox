package no.acntech.sandbox.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.stream.Collectors;

import no.acntech.sandbox.config.ElasticsearchProperties;
import no.acntech.sandbox.model.SearchHit;
import no.acntech.sandbox.model.SearchRequest;
import no.acntech.sandbox.model.SearchResponse;

@Component
public class SearchWebClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchWebClient.class);
    private final ObjectMapper objectMapper;
    private final WebClient webClient;
    private final ElasticsearchProperties elasticsearchProperties;

    public SearchWebClient(final ObjectMapper objectMapper,
                           final WebClient webClient,
                           final ElasticsearchProperties elasticsearchProperties) {
        this.objectMapper = objectMapper;
        this.webClient = webClient;
        this.elasticsearchProperties = elasticsearchProperties;
    }

    public <S, T> Page<T> search(final S query, final Pageable pageable, final Class<T> hitClass) {
        final var responseType = new ParameterizedTypeReference<SearchResponse<T>>() {
        };
        final var uri = searchUri(hitClass);
        LOGGER.debug("Search request URI: {}", uri);
        final var request = createRequest(query, pageable);
        debugLog("Search request body: {}", request);
        final var response = webClient.post()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(responseType)
                .block();
        debugLog("Search response body: {}", response);
        return createResponse(response, pageable);
    }

    private <T> URI searchUri(final Class<T> hitClass) {
        final var uriBuilder = UriComponentsBuilder.fromUri(elasticsearchProperties.getHosts().get(0));
        final var annotation = hitClass.getAnnotation(Document.class);
        if (annotation != null && StringUtils.hasText(annotation.indexName())) {
            uriBuilder.pathSegment(annotation.indexName());
        }
        return uriBuilder
                .pathSegment("_search")
                .build()
                .toUri();
    }

    private <S> SearchRequest<S> createRequest(final S query, final Pageable pageable) {
        return SearchRequest.<S>builder()
                .trackTotalHits(true)
                .from(pageable.getOffset())
                .size(pageable.getPageSize())
                .query(query)
                .build();
    }

    private <T> Page<T> createResponse(final SearchResponse<T> response, final Pageable pageable) {
        if (response == null) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        } else {
            final var searchHits = response.getHits();
            final var searchPageable = searchHits.getPageable();
            final var hits = searchHits.getHits().stream()
                    .map(SearchHit::getSource)
                    .collect(Collectors.toList());
            return new PageImpl<>(hits, pageable, searchPageable.getTotalElements());
        }
    }

    private void debugLog(String format, final Object body) {
        if (LOGGER.isDebugEnabled()) {
            try {
                final var json = objectMapper.writeValueAsString(body);
                LOGGER.debug(format, json);
            } catch (JsonProcessingException e) {
                LOGGER.warn("Could not serialize body", e);
            }
        }
    }
}

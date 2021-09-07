package no.acntech.sandbox.client;

import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SearchClient {

    private final ElasticsearchOperations elasticsearchOperations;

    public SearchClient(final ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public <T> Page<T> search(String query, final Pageable pageable, Class<T> responseClass) {
        final var queryBuilder = new SimpleQueryStringBuilder(query);
        final var nativeSearchQuery = new NativeSearchQueryBuilder()
                .withTrackTotalHits(true)
                .withPageable(pageable)
                .withQuery(queryBuilder)
                .build();
        final var hits = elasticsearchOperations.search(nativeSearchQuery, responseClass);
        final var totalHits = hits.getTotalHits();
        final var data = hits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
        return new PageImpl<>(data, pageable, totalHits);
    }
}

package no.acntech.sandbox.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SearchClient {

    private final ElasticsearchOperations elasticsearchOperations;

    public SearchClient(final ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public <T> Page<T> search(final Query query, final Pageable pageable, Class<T> responseClass) {
        final var hits = elasticsearchOperations.search(query, responseClass);
        final var totalHits = hits.getTotalHits();
        final var data = hits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
        return new PageImpl<>(data, pageable, totalHits);
    }
}

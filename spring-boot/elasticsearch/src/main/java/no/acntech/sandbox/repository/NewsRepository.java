package no.acntech.sandbox.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import no.acntech.sandbox.model.NewsDocument;

public interface NewsRepository extends ElasticsearchRepository<NewsDocument, String> {
}

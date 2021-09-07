package no.acntech.sandbox.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import no.acntech.sandbox.model.News;

public interface NewsRepository extends ElasticsearchRepository<News, String> {
}

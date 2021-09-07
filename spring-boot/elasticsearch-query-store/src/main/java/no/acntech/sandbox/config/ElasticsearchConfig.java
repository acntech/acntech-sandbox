package no.acntech.sandbox.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.lang.NonNull;

@EnableConfigurationProperties({ElasticsearchProperties.class})
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    private final ElasticsearchProperties properties;

    public ElasticsearchConfig(final ElasticsearchProperties properties) {
        this.properties = properties;
    }

    @Bean
    @NonNull
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final var clientConfiguration = ClientConfiguration.builder()
                .connectedTo(properties.getHosts())
                .build();
        return RestClients.create(clientConfiguration)
                .rest();
    }
}

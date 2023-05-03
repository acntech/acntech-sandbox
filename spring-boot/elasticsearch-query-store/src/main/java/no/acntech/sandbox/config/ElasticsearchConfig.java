package no.acntech.sandbox.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.lang.NonNull;

@EnableConfigurationProperties({ElasticsearchProperties.class})
@Configuration
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    private final ElasticsearchProperties properties;

    public ElasticsearchConfig(final ElasticsearchProperties properties) {
        this.properties = properties;
    }

    @NonNull
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(properties.getHosts())
                .build();
    }
}

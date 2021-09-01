package no.acntech.sandbox.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration(proxyBeanMethods = false)
public class KafkaConfig {

    @Bean
    public NewTopic testTopic() {
        return TopicBuilder.name("acntech.sandbox.test.data")
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }
}

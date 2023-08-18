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
    public NewTopic greetingTopic() {
        return TopicBuilder.name("acntech.sandbox.greetings")
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }
}

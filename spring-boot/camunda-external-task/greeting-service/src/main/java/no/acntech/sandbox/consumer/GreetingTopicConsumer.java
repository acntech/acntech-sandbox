package no.acntech.sandbox.consumer;

import jakarta.annotation.PostConstruct;
import org.camunda.bpm.client.ExternalTaskClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import no.acntech.sandbox.config.WorkflowProperties;
import no.acntech.sandbox.handler.GenerateGreetingTaskHandler;

@Component
public class GreetingTopicConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingTopicConsumer.class);
    private final WorkflowProperties workflowProperties;
    private final GenerateGreetingTaskHandler generateGreetingTaskHandler;

    public GreetingTopicConsumer(final WorkflowProperties workflowProperties,
                                 final GenerateGreetingTaskHandler generateGreetingTaskHandler) {
        this.workflowProperties = workflowProperties;
        this.generateGreetingTaskHandler = generateGreetingTaskHandler;
    }

    @PostConstruct
    public void subscribeToTopics() {
        try {
            LOGGER.info("Starting subscribing to task topics...");
            ExternalTaskClient client = ExternalTaskClient.create()
                    .baseUrl(workflowProperties.getBaseUrl())
                    .asyncResponseTimeout(workflowProperties.getResponseTimeout().toMillis())
                    .build();

            client.subscribe("GenerateGreetingTopic")
                    .lockDuration(workflowProperties.getLockDuration().toMillis())
                    .handler(generateGreetingTaskHandler)
                    .open();
        } catch (Exception e) {
            LOGGER.error("Subscription to task topics failed with error", e);
        } finally {
            LOGGER.info("Completed subscribing to task topics");
        }
    }
}

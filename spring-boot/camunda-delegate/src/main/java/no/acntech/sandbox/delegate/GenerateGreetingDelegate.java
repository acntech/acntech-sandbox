package no.acntech.sandbox.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GenerateGreetingDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateGreetingDelegate.class);

    @Override
    public void execute(final DelegateExecution delegateExecution) {
        try {
            LOGGER.info("Starting handling of task '{}'...", delegateExecution.getActivityInstanceId());
            // Get process variable
            Object firstName = delegateExecution.getVariable("FirstName");
            // Set new process variable
            delegateExecution.setVariable("Greeting", "Hello " + firstName + "!");
        } catch (Exception e) {
            LOGGER.error("Handling of task '" + delegateExecution.getActivityInstanceId() + "' failed with error", e);
        } finally {
            LOGGER.info("Completed handling of task '{}'", delegateExecution.getActivityInstanceId());
        }
    }
}

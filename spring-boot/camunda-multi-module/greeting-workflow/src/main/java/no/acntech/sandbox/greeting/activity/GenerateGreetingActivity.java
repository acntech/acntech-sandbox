package no.acntech.sandbox.greeting.activity;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GenerateGreetingActivity implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateGreetingActivity.class);

    @Override
    public void execute(final DelegateExecution delegateExecution) {
        try {
            LOGGER.debug("Starting handling of task '{}'...", delegateExecution.getActivityInstanceId());
            // Get process variable
            Object firstName = delegateExecution.getVariable("FirstName");
            // Set new process variable
            delegateExecution.setVariable("Greeting", "Hello " + firstName + "!");
        } catch (Exception e) {
            LOGGER.error("Handling of task '" + delegateExecution.getActivityInstanceId() + "' failed with error", e);
        } finally {
            LOGGER.debug("Completed handling of task '{}'", delegateExecution.getActivityInstanceId());
        }
    }
}

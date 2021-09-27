package no.acntech.sandbox.farewell.activity;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogFarewellActivity implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFarewellActivity.class);

    @Override
    public void execute(final DelegateExecution delegateExecution) {
        try {
            LOGGER.debug("Starting handling of task '{}'...", delegateExecution.getActivityInstanceId());
            Object greeting = delegateExecution.getVariable("Farewell");
            LOGGER.info(greeting.toString());
        } catch (Exception e) {
            LOGGER.error("Handling of task '" + delegateExecution.getActivityInstanceId() + "' failed with error", e);
        } finally {
            LOGGER.debug("Completed handling of task '{}'", delegateExecution.getActivityInstanceId());
        }
    }
}

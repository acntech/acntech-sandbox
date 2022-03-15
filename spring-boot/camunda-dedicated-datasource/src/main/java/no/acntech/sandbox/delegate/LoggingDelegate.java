package no.acntech.sandbox.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingDelegate.class);

    @Override
    public void execute(final DelegateExecution delegateExecution) {
        Object greeting = delegateExecution.getVariable("Greeting");
        LOGGER.info(greeting.toString());
    }
}

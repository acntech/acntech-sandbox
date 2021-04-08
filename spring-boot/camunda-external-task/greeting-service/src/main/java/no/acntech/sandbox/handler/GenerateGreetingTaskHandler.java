package no.acntech.sandbox.handler;

import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GenerateGreetingTaskHandler implements ExternalTaskHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateGreetingTaskHandler.class);

    @Override
    public void execute(final ExternalTask externalTask,
                        final ExternalTaskService externalTaskService) {
        try {
            LOGGER.info("Starting handling of external task '{}'...", externalTask.getActivityInstanceId());
            // Get process variable
            String firstName = externalTask.getVariable("FirstName");
            // Set new process variable
            VariableMap variables = Variables.createVariables();
            variables.put("Greeting", "Hello " + firstName + "!");
            // Complete task
            externalTaskService.complete(externalTask, variables);
        } catch (Exception e) {
            LOGGER.error("Handling of external task '" + externalTask.getActivityInstanceId() + "' failed with error", e);
        } finally {
            LOGGER.info("Completed handling of external task '{}'", externalTask.getActivityInstanceId());
        }
    }
}

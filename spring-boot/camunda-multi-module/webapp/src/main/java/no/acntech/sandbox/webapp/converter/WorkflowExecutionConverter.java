package no.acntech.sandbox.webapp.converter;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import no.acntech.sandbox.webapp.model.WorkflowExecution;

@Component
public class WorkflowExecutionConverter implements Converter<ProcessInstance, WorkflowExecution> {

    @NonNull
    @Override
    public WorkflowExecution convert(@NonNull final ProcessInstance source) {
        return WorkflowExecution.builder()
                .processExecutionId(source.getId())
                .processInstanceId(source.getProcessInstanceId())
                .parentProcessInstanceId(source.getRootProcessInstanceId())
                .processDefinitionId(source.getProcessDefinitionId())
                .build();
    }
}

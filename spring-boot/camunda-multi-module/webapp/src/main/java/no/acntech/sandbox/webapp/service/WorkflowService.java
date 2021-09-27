package no.acntech.sandbox.webapp.service;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import no.acntech.sandbox.webapp.model.WorkflowExecution;

@Service
public class WorkflowService {

    private final ConversionService conversionService;
    private final ProcessEngine processEngine;

    public WorkflowService(final ConversionService conversionService,
                           final ProcessEngine processEngine) {
        this.conversionService = conversionService;
        this.processEngine = processEngine;
    }

    public WorkflowExecution getProcess(String processDefinitionId, String processExecutionId) {
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionId)
                .processInstanceId(processExecutionId)
                .singleResult();
        return mapWorkflowExecution(processInstance);
    }

    public List<WorkflowExecution> findProcesses(String processDefinitionId) {
        List<ProcessInstance> processInstances = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionId)
                .list();
        return processInstances.stream()
                .map(this::mapWorkflowExecution)
                .collect(Collectors.toList());
    }

    public WorkflowExecution startProcess(String processDefinitionId, Map<String, Object> variables) {
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceByKey(processDefinitionId)
                .setVariables(variables)
                .execute();
        return mapWorkflowExecution(processInstance);
    }

    private WorkflowExecution mapWorkflowExecution(final ProcessInstance processInstance) {
        return conversionService.convert(processInstance, WorkflowExecution.class);
    }
}

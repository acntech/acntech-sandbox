package no.acntech.sandbox.webapp.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import no.acntech.sandbox.webapp.model.WorkflowExecution;

@Service
public class FarewellService {

    private static final String PROCESS_DEFINITION_ID = "FarewellProcess";
    private final WorkflowService workflowService;

    public FarewellService(final WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    public WorkflowExecution getProcess(String processExecutionId) {
        return workflowService.getProcess(PROCESS_DEFINITION_ID, processExecutionId);
    }

    public List<WorkflowExecution> findProcesses() {
        return workflowService.findProcesses(PROCESS_DEFINITION_ID);
    }

    public WorkflowExecution startProcess(String firstName) {
        Map<String, Object> variables = Map.of("FirstName", firstName);
        return workflowService.startProcess(PROCESS_DEFINITION_ID, variables);
    }
}

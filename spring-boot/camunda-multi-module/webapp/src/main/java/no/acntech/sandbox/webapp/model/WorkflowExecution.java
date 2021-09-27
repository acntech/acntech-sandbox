package no.acntech.sandbox.webapp.model;

public class WorkflowExecution {

    private String processExecutionId;
    private String processInstanceId;
    private String parentProcessInstanceId;
    private String processDefinitionId;

    private WorkflowExecution() {
    }

    public String getProcessExecutionId() {
        return processExecutionId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public String getParentProcessInstanceId() {
        return parentProcessInstanceId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String processExecutionId;
        private String processInstanceId;
        private String parentProcessInstanceId;
        private String processDefinitionId;

        private Builder() {
        }

        public Builder processExecutionId(String processExecutionId) {
            this.processExecutionId = processExecutionId;
            return this;
        }

        public Builder processInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
            return this;
        }

        public Builder parentProcessInstanceId(String parentProcessInstanceId) {
            this.parentProcessInstanceId = parentProcessInstanceId;
            return this;
        }

        public Builder processDefinitionId(String processDefinitionId) {
            this.processDefinitionId = processDefinitionId;
            return this;
        }

        public WorkflowExecution build() {
            WorkflowExecution workflowExecution = new WorkflowExecution();
            workflowExecution.processExecutionId = this.processExecutionId;
            workflowExecution.processInstanceId = this.processInstanceId;
            workflowExecution.parentProcessInstanceId = this.parentProcessInstanceId;
            workflowExecution.processDefinitionId = this.processDefinitionId;
            return workflowExecution;
        }
    }
}

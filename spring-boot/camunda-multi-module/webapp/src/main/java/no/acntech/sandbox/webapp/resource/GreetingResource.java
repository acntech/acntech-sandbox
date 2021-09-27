package no.acntech.sandbox.webapp.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import no.acntech.sandbox.webapp.model.Person;
import no.acntech.sandbox.webapp.model.WorkflowExecution;
import no.acntech.sandbox.webapp.service.GreetingService;

@RequestMapping(path = "/api/greeting")
@RestController
public class GreetingResource {

    private final GreetingService greetingService;

    public GreetingResource(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WorkflowExecution> get(@PathVariable(name = "id") String id) {
        WorkflowExecution workflowExecution = greetingService.getProcess(id);
        return ResponseEntity.ok(workflowExecution);
    }

    @GetMapping
    public ResponseEntity<List<WorkflowExecution>> find() {
        List<WorkflowExecution> workflowExecutions = greetingService.findProcesses();
        return ResponseEntity.ok(workflowExecutions);
    }

    @PostMapping
    public ResponseEntity<WorkflowExecution> greeting(@Valid @RequestBody final Person person) {
        WorkflowExecution workflowExecution = greetingService.startProcess(person.getFirstName());
        return ResponseEntity.accepted().body(workflowExecution);
    }
}

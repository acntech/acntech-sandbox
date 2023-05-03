package no.acntech.sandbox.webapp.resource;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import no.acntech.sandbox.webapp.model.Person;
import no.acntech.sandbox.webapp.model.WorkflowExecution;
import no.acntech.sandbox.webapp.service.FarewellService;

@RequestMapping(path = "/api/farewell")
@RestController
public class FarewellResource {

    private final FarewellService farewellService;

    public FarewellResource(final FarewellService farewellService) {
        this.farewellService = farewellService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WorkflowExecution> get(@PathVariable(name = "id") String id) {
        WorkflowExecution workflowExecution = farewellService.getProcess(id);
        return ResponseEntity.ok(workflowExecution);
    }

    @GetMapping
    public ResponseEntity<List<WorkflowExecution>> find() {
        List<WorkflowExecution> workflowExecutions = farewellService.findProcesses();
        return ResponseEntity.ok(workflowExecutions);
    }

    @PostMapping
    public ResponseEntity<WorkflowExecution> greeting(@Valid @RequestBody final Person person) {
        WorkflowExecution workflowExecution = farewellService.startProcess(person.getFirstName());
        return ResponseEntity.accepted().body(workflowExecution);
    }
}

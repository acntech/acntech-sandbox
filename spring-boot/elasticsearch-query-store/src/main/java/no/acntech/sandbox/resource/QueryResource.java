package no.acntech.sandbox.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import no.acntech.sandbox.model.CreateQueryDto;
import no.acntech.sandbox.model.QueryDto;
import no.acntech.sandbox.model.UpdateQueryDto;
import no.acntech.sandbox.service.QueryService;

@RequestMapping(path = "/api/queries")
@RestController
public class QueryResource {

    private final QueryService queryService;

    public QueryResource(final QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<QueryDto> get(@PathVariable(name = "id") Long id) {
        final var query = queryService.get(id);
        return ResponseEntity.ok(query);
    }

    @GetMapping
    public ResponseEntity<List<QueryDto>> find() {
        final var queries = queryService.find();
        return ResponseEntity.ok(queries);
    }

    @PostMapping
    public ResponseEntity<QueryDto> post(@Valid @RequestBody final CreateQueryDto createQuery) {
        final var query = queryService.create(createQuery);
        final var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .pathSegment(String.valueOf(query.getId()))
                .build()
                .toUri();
        return ResponseEntity
                .created(location)
                .body(query);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<QueryDto> patch(@PathVariable(name = "id") Long id,
                                          @Valid @RequestBody final UpdateQueryDto updateQuery) {
        final var query = queryService.update(id, updateQuery);
        return ResponseEntity.ok(query);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        queryService.delete(id);
        return ResponseEntity.ok().build();
    }
}

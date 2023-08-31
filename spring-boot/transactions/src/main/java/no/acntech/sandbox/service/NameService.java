package no.acntech.sandbox.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

import no.acntech.sandbox.model.NameDto;
import no.acntech.sandbox.model.NameEntity;
import no.acntech.sandbox.repository.NameRepository;

@Service
public class NameService {

    private final NameRepository nameRepository;

    public NameService(final NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    public List<NameDto> findNames() {
        return nameRepository.findAll().stream()
                .map(entity -> new NameDto(entity.getId(), entity.getName()))
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public NameDto saveName(@NotBlank final String name) {
        final var entity = nameRepository.save(new NameEntity(name));
        return new NameDto(entity.getId(), entity.getName());
    }
}

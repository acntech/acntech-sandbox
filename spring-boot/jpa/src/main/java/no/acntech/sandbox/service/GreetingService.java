package no.acntech.sandbox.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import no.acntech.sandbox.model.GreetingDto;
import no.acntech.sandbox.model.GreetingEntity;
import no.acntech.sandbox.repository.GreetingRepository;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public List<GreetingDto> findGreetings() {
        final var greetingEntities = greetingRepository.findAll();
        return greetingEntities.stream()
                .map(GreetingEntity::getMessage)
                .map(GreetingDto::new)
                .collect(Collectors.toList());
    }
}

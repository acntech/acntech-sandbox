package no.acntech.sandbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import no.acntech.sandbox.model.GreetingDto;
import no.acntech.sandbox.repository.GreetingRepository;

@Service
public class GreetingQueryService {

    private final GreetingRepository greetingRepository;

    @Autowired
    public GreetingQueryService(final GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public List<GreetingDto> findGreetings() {
        final var greetings = greetingRepository.findAllByOrderByCreatedDesc();
        return greetings.stream()
                .map(entity -> new GreetingDto(entity.getMessage(), entity.getCreated().toEpochMilli()))
                .collect(Collectors.toList());
    }

    public List<GreetingDto> findGreetingsSince(final Instant since) {
        final var greetings = greetingRepository.findAllByCreatedAfterOrderByCreatedDesc(since);
        return greetings.stream()
                .map(entity -> new GreetingDto(entity.getMessage(), entity.getCreated().toEpochMilli()))
                .collect(Collectors.toList());
    }
}

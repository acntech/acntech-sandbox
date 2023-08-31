package no.acntech.sandbox.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Random;

import no.acntech.sandbox.model.PrefixDto;
import no.acntech.sandbox.model.PrefixEntity;
import no.acntech.sandbox.repository.PrefixRepository;

@Service
public class PrefixService {

    private static final Random RANDOM = new Random();
    private static final List<String> PREFIXES = List.of("Hi", "Hello", "Howdy");
    private final PrefixRepository prefixRepository;

    public PrefixService(final PrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    public PrefixDto getPrefix(@NotNull final Long nameId) {
        final var entity = prefixRepository.findByNameId(nameId);
        return new PrefixDto(entity.getId(), entity.getPrefix());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void savePrefix(@NotNull final Long nameId) {
        final var prefix = PREFIXES.get(RANDOM.nextInt(PREFIXES.size()));
        prefixRepository.save(new PrefixEntity(nameId, prefix));
    }
}

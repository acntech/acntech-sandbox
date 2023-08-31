package no.acntech.sandbox.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

import no.acntech.sandbox.model.GreetingDto;

@Service
public class GreetingService {

    private final NameService nameService;
    private final PrefixService prefixService;

    public GreetingService(final NameService nameService,
                           final PrefixService prefixService) {
        this.nameService = nameService;
        this.prefixService = prefixService;
    }

    public List<GreetingDto> findGreetings() {
        return nameService.findNames().stream()
                .map(nameDto -> {
                    final var prefixDto = prefixService.getPrefix(nameDto.id());
                    return new GreetingDto(prefixDto.prefix() + " " + nameDto.name() + "!");
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void createGreeting(@NotBlank final String name) {
        final var nameDto = nameService.saveName(name);
        prefixService.savePrefix(nameDto.id());
    }
}

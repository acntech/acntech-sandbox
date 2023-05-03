package no.acntech.sandbox.service;

import jakarta.validation.Valid;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import no.acntech.sandbox.model.BarEntity;
import no.acntech.sandbox.model.CreateBarDto;
import no.acntech.sandbox.model.ReadBarDto;
import no.acntech.sandbox.repository.BarRepository;

@Service
public class BarService {

    private final ConversionService conversionService;
    private final BarRepository barRepository;

    public BarService(final ConversionService conversionService,
                      final BarRepository barRepository) {
        this.conversionService = conversionService;
        this.barRepository = barRepository;
    }

    public List<ReadBarDto> readAll() {
        List<BarEntity> bars = barRepository.findAll();
        return bars.stream()
                .map(bar -> conversionService.convert(bar, ReadBarDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public ReadBarDto create(@Valid final CreateBarDto createBar) {
        final BarEntity bar = conversionService.convert(createBar, BarEntity.class);
        BarEntity createdBar = barRepository.save(bar);
        return conversionService.convert(createdBar, ReadBarDto.class);
    }
}

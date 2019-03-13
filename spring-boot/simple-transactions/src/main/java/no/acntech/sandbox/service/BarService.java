package no.acntech.sandbox.service;

import no.acntech.sandbox.domain.Bar;
import no.acntech.sandbox.dto.CreateBar;
import no.acntech.sandbox.dto.ReadBar;
import no.acntech.sandbox.repository.BarRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarService {

    private final ConversionService conversionService;
    private final BarRepository barRepository;

    public BarService(final ConversionService conversionService,
                      final BarRepository barRepository) {
        this.conversionService = conversionService;
        this.barRepository = barRepository;
    }

    public List<ReadBar> readAll() {
        List<Bar> bars = barRepository.findAll();
        return bars.stream()
                .map(bar -> conversionService.convert(bar, ReadBar.class))
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public ReadBar create(final CreateBar createBar) {
        final Bar bar = conversionService.convert(createBar, Bar.class);
        Bar createdBar = barRepository.save(bar);
        return conversionService.convert(createdBar, ReadBar.class);
    }
}

package no.acntech.sandbox.service;

import jakarta.validation.Valid;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import no.acntech.sandbox.model.CreateBarDto;
import no.acntech.sandbox.model.CreateFooDto;
import no.acntech.sandbox.model.ReadBarDto;
import no.acntech.sandbox.model.ReadFooDto;

@Service
public class DefaultService {

    private final ConversionService conversionService;
    private final FooService fooService;
    private final BarService barService;

    public DefaultService(final ConversionService conversionService,
                          final FooService fooService,
                          final BarService barService) {
        this.conversionService = conversionService;
        this.fooService = fooService;
        this.barService = barService;
    }

    public List<ReadFooDto> readAllFoo() {
        return fooService.readAll();
    }

    @Transactional
    public void createFoo(@Valid final CreateFooDto createFoo) {
        ReadFooDto readFoo = fooService.create(createFoo);
        CreateBarDto createBar = conversionService.convert(readFoo, CreateBarDto.class);
        barService.create(createBar);
    }

    public List<ReadBarDto> readAllBar() {
        return barService.readAll();
    }
}

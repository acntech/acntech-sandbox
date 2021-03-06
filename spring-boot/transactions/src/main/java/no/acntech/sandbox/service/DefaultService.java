package no.acntech.sandbox.service;

import no.acntech.sandbox.dto.CreateBar;
import no.acntech.sandbox.dto.CreateFoo;
import no.acntech.sandbox.dto.ReadBar;
import no.acntech.sandbox.dto.ReadFoo;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<ReadFoo> readAllFoo() {
        return fooService.readAll();
    }

    @Transactional
    public void createFoo(final CreateFoo createFoo) {
        ReadFoo readFoo = fooService.create(createFoo);
        CreateBar createBar = conversionService.convert(readFoo, CreateBar.class);
        barService.create(createBar);
    }

    public List<ReadBar> readAllBar() {
        return barService.readAll();
    }
}

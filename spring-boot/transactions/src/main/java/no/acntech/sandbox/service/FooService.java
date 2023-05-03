package no.acntech.sandbox.service;

import jakarta.validation.Valid;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import no.acntech.sandbox.model.CreateFooDto;
import no.acntech.sandbox.model.FooEntity;
import no.acntech.sandbox.model.ReadFooDto;
import no.acntech.sandbox.repository.FooRepository;

@Service
public class FooService {

    private final ConversionService conversionService;
    private final FooRepository fooRepository;

    public FooService(final ConversionService conversionService,
                      final FooRepository fooRepository) {
        this.conversionService = conversionService;
        this.fooRepository = fooRepository;
    }


    public List<ReadFooDto> readAll() {
        List<FooEntity> foos = fooRepository.findAll();
        return foos.stream()
                .map(foo -> conversionService.convert(foo, ReadFooDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ReadFooDto create(@Valid final CreateFooDto createFoo) {
        final FooEntity foo = conversionService.convert(createFoo, FooEntity.class);
        final FooEntity createdFoo = fooRepository.save(foo);
        return conversionService.convert(createdFoo, ReadFooDto.class);
    }
}

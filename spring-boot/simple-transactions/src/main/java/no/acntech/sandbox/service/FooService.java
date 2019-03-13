package no.acntech.sandbox.service;

import no.acntech.sandbox.domain.Foo;
import no.acntech.sandbox.dto.CreateFoo;
import no.acntech.sandbox.dto.ReadFoo;
import no.acntech.sandbox.repository.FooRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FooService {

    private final ConversionService conversionService;
    private final FooRepository fooRepository;

    public FooService(final ConversionService conversionService,
                      final FooRepository fooRepository) {
        this.conversionService = conversionService;
        this.fooRepository = fooRepository;
    }


    public List<ReadFoo> readAll() {
        List<Foo> foos = fooRepository.findAll();
        return foos.stream()
                .map(foo -> conversionService.convert(foo, ReadFoo.class))
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ReadFoo create(final CreateFoo createFoo) {
        final Foo foo = conversionService.convert(createFoo, Foo.class);
        final Foo createdFoo = fooRepository.save(foo);
        return conversionService.convert(createdFoo, ReadFoo.class);
    }
}

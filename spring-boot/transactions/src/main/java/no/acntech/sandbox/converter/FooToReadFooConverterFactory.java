package no.acntech.sandbox.converter;

import no.acntech.sandbox.domain.Foo;
import no.acntech.sandbox.dto.ReadFoo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

@Component
public class FooToReadFooConverterFactory implements ConverterFactory<Foo, ReadFoo> {

    public FooToReadFooConverterFactory(final ConfigurableConversionService conversionService) {
        conversionService.addConverterFactory(this);
    }

    @Override
    public <T extends ReadFoo> Converter<Foo, T> getConverter(Class<T> clazz) {
        return new ReadFooConverter<>();
    }

    public static class ReadFooConverter<T extends ReadFoo> implements Converter<Foo, T> {

        @Override
        public T convert(final Foo foo) {
            ReadFoo readFoo = new ReadFoo();
            readFoo.setId(foo.getId());
            readFoo.setData(foo.getData());
            return (T) readFoo;
        }
    }
}

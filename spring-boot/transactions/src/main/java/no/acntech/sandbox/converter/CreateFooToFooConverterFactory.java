package no.acntech.sandbox.converter;

import no.acntech.sandbox.domain.Foo;
import no.acntech.sandbox.dto.CreateFoo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

@Component
public class CreateFooToFooConverterFactory implements ConverterFactory<CreateFoo, Foo> {

    public CreateFooToFooConverterFactory(final ConfigurableConversionService conversionService) {
        conversionService.addConverterFactory(this);
    }

    @Override
    public <T extends Foo> Converter<CreateFoo, T> getConverter(Class<T> clazz) {
        return new ReadFooConverter<>();
    }

    public static class ReadFooConverter<T extends Foo> implements Converter<CreateFoo, T> {

        @Override
        public T convert(final CreateFoo createFoo) {
            Foo foo = new Foo();
            foo.setData(createFoo.getData());
            return (T) foo;
        }
    }
}

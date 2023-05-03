package no.acntech.sandbox.converter;

import no.acntech.sandbox.model.FooEntity;
import no.acntech.sandbox.model.CreateFooDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

@Component
public class CreateFooToFooConverterFactory implements ConverterFactory<CreateFooDto, FooEntity> {

    public CreateFooToFooConverterFactory(final ConfigurableConversionService conversionService) {
        conversionService.addConverterFactory(this);
    }

    @Override
    public <T extends FooEntity> Converter<CreateFooDto, T> getConverter(Class<T> clazz) {
        return new ReadFooConverter<>();
    }

    public static class ReadFooConverter<T extends FooEntity> implements Converter<CreateFooDto, T> {

        @Override
        public T convert(final CreateFooDto createFoo) {
            FooEntity foo = new FooEntity();
            foo.setData(createFoo.getData());
            return (T) foo;
        }
    }
}

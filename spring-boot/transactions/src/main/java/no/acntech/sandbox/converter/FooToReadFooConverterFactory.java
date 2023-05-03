package no.acntech.sandbox.converter;

import no.acntech.sandbox.model.FooEntity;
import no.acntech.sandbox.model.ReadFooDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

@Component
public class FooToReadFooConverterFactory implements ConverterFactory<FooEntity, ReadFooDto> {

    public FooToReadFooConverterFactory(final ConfigurableConversionService conversionService) {
        conversionService.addConverterFactory(this);
    }

    @Override
    public <T extends ReadFooDto> Converter<FooEntity, T> getConverter(Class<T> clazz) {
        return new ReadFooConverter<>();
    }

    public static class ReadFooConverter<T extends ReadFooDto> implements Converter<FooEntity, T> {

        @Override
        public T convert(final FooEntity foo) {
            ReadFooDto readFoo = new ReadFooDto();
            readFoo.setId(foo.getId());
            readFoo.setData(foo.getData());
            return (T) readFoo;
        }
    }
}

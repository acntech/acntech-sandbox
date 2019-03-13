package no.acntech.sandbox.converter;

import no.acntech.sandbox.dto.CreateBar;
import no.acntech.sandbox.dto.ReadFoo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

@Component
public class ReadFooToCreateBarConverterFactory implements ConverterFactory<ReadFoo, CreateBar> {

    public ReadFooToCreateBarConverterFactory(final ConfigurableConversionService conversionService) {
        conversionService.addConverterFactory(this);
    }

    @Override
    public <T extends CreateBar> Converter<ReadFoo, T> getConverter(Class<T> clazz) {
        return new ReadFooConverter<>();
    }

    public static class ReadFooConverter<T extends CreateBar> implements Converter<ReadFoo, T> {

        @Override
        public T convert(final ReadFoo readFoo) {
            CreateBar createBar = new CreateBar();
            createBar.setFooId(readFoo.getId());
            createBar.setData(readFoo.getData());
            return (T) createBar;
        }
    }
}

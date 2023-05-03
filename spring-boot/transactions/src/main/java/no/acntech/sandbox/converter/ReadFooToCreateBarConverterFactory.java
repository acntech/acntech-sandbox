package no.acntech.sandbox.converter;

import no.acntech.sandbox.model.CreateBarDto;
import no.acntech.sandbox.model.ReadFooDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

@Component
public class ReadFooToCreateBarConverterFactory implements ConverterFactory<ReadFooDto, CreateBarDto> {

    public ReadFooToCreateBarConverterFactory(final ConfigurableConversionService conversionService) {
        conversionService.addConverterFactory(this);
    }

    @Override
    public <T extends CreateBarDto> Converter<ReadFooDto, T> getConverter(Class<T> clazz) {
        return new ReadFooConverter<>();
    }

    public static class ReadFooConverter<T extends CreateBarDto> implements Converter<ReadFooDto, T> {

        @Override
        public T convert(final ReadFooDto readFoo) {
            CreateBarDto createBar = new CreateBarDto();
            createBar.setFooId(readFoo.getId());
            createBar.setData(readFoo.getData());
            return (T) createBar;
        }
    }
}

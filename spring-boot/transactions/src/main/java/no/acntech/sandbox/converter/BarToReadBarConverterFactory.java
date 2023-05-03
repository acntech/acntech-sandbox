package no.acntech.sandbox.converter;

import no.acntech.sandbox.model.BarEntity;
import no.acntech.sandbox.model.ReadBarDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

@Component
public class BarToReadBarConverterFactory implements ConverterFactory<BarEntity, ReadBarDto> {

    public BarToReadBarConverterFactory(final ConfigurableConversionService conversionService) {
        conversionService.addConverterFactory(this);
    }

    @Override
    public <T extends ReadBarDto> Converter<BarEntity, T> getConverter(Class<T> clazz) {
        return new ReadFooConverter<>();
    }

    public static class ReadFooConverter<T extends ReadBarDto> implements Converter<BarEntity, T> {

        @Override
        public T convert(final BarEntity bar) {
            ReadBarDto readBar = new ReadBarDto();
            readBar.setId(bar.getId());
            readBar.setFooId(bar.getFooId());
            readBar.setData(bar.getData());
            return (T) readBar;
        }
    }
}

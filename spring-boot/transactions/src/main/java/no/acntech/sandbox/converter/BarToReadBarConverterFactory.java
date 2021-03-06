package no.acntech.sandbox.converter;

import no.acntech.sandbox.domain.Bar;
import no.acntech.sandbox.dto.ReadBar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

@Component
public class BarToReadBarConverterFactory implements ConverterFactory<Bar, ReadBar> {

    public BarToReadBarConverterFactory(final ConfigurableConversionService conversionService) {
        conversionService.addConverterFactory(this);
    }

    @Override
    public <T extends ReadBar> Converter<Bar, T> getConverter(Class<T> clazz) {
        return new ReadFooConverter<>();
    }

    public static class ReadFooConverter<T extends ReadBar> implements Converter<Bar, T> {

        @Override
        public T convert(final Bar bar) {
            ReadBar readBar = new ReadBar();
            readBar.setId(bar.getId());
            readBar.setFooId(bar.getFooId());
            readBar.setData(bar.getData());
            return (T) readBar;
        }
    }
}

package no.acntech.sandbox.converter;

import no.acntech.sandbox.domain.Bar;
import no.acntech.sandbox.dto.CreateBar;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

@Component
public class CreateBarToBarConverterFactory implements ConverterFactory<CreateBar, Bar> {

    public CreateBarToBarConverterFactory(final ConfigurableConversionService conversionService) {
        conversionService.addConverterFactory(this);
    }

    @Override
    public <T extends Bar> Converter<CreateBar, T> getConverter(Class<T> clazz) {
        return new ReadFooConverter<>();
    }

    public static class ReadFooConverter<T extends Bar> implements Converter<CreateBar, T> {

        @Override
        public T convert(final CreateBar createBar) {
            Bar bar = new Bar();
            bar.setFooId(createBar.getFooId());
            bar.setData(createBar.getData());
            return (T) bar;
        }
    }
}

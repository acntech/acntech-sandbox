package no.acntech.sandbox.converter;

import no.acntech.sandbox.model.BarEntity;
import no.acntech.sandbox.model.CreateBarDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;

@Component
public class CreateBarToBarConverterFactory implements ConverterFactory<CreateBarDto, BarEntity> {

    public CreateBarToBarConverterFactory(final ConfigurableConversionService conversionService) {
        conversionService.addConverterFactory(this);
    }

    @Override
    public <T extends BarEntity> Converter<CreateBarDto, T> getConverter(Class<T> clazz) {
        return new ReadFooConverter<>();
    }

    public static class ReadFooConverter<T extends BarEntity> implements Converter<CreateBarDto, T> {

        @Override
        public T convert(final CreateBarDto createBar) {
            BarEntity bar = new BarEntity();
            bar.setFooId(createBar.getFooId());
            bar.setData(createBar.getData());
            return (T) bar;
        }
    }
}

package no.acntech.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.List;

@SpringBootApplication
public class SpringBootElasticsearchQueryStoreApplication {

    @Primary
    @Bean
    public ConversionService applicationConversionService(final List<Converter<?, ?>> converters,
                                                          final List<GenericConverter> genericConverters) {
        if (genericConverters.isEmpty() && converters.isEmpty()) {
            return ApplicationConversionService.getSharedInstance();
        }
        ApplicationConversionService conversionService = new ApplicationConversionService();
        converters.forEach(conversionService::addConverter);
        genericConverters.forEach(conversionService::addConverter);
        return conversionService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootElasticsearchQueryStoreApplication.class, args);
    }
}

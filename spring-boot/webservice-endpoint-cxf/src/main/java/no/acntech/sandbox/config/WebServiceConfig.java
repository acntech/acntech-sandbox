package no.acntech.sandbox.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

import no.acntech.sandbox.service.GreetingWebService;

@Configuration
public class WebServiceConfig {

    private final Bus bus;

    public WebServiceConfig(final Bus bus) {
        this.bus = bus;
    }

    @Bean
    public Endpoint greetingEndpoint(final GreetingWebService greetingWebService) {
        EndpointImpl endpoint = new EndpointImpl(bus, greetingWebService);
        endpoint.publish("/v1/greeting");
        return endpoint;
    }
}

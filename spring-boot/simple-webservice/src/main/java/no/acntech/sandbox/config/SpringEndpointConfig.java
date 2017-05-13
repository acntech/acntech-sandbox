package no.acntech.sandbox.config;

import no.acntech.sandbox.endpoint.SimpleEndpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class SpringEndpointConfig {

    private final Bus bus;

    @Autowired
    public SpringEndpointConfig(final Bus bus) {
        this.bus = bus;
    }

    @Autowired
    @Bean
    public Endpoint endpoint(final SimpleEndpoint simpleEndpoint) {
        EndpointImpl endpoint = new EndpointImpl(bus, simpleEndpoint);
        endpoint.publish("/simple");
        return endpoint;
    }
}

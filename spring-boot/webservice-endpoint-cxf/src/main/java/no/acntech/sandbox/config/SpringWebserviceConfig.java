package no.acntech.sandbox.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

import no.acntech.sandbox.webservice.endpoint.GreetingService;
import no.acntech.sandbox.webservice.greeting.v1.GreetingPortType;

@Configuration
public class SpringWebserviceConfig {

    private final Bus bus;

    public SpringWebserviceConfig(final Bus bus) {
        this.bus = bus;
    }

    @Bean
    public Endpoint greetingEndpoint(final GreetingService simpleService,
                                     @Value("${acntech.webservice.endpoint.path}") String webServicePath) {
        EndpointImpl endpoint = new EndpointImpl(bus, simpleService);
        endpoint.publish(webServicePath);
        return endpoint;
    }

    @Bean
    public GreetingPortType greetingConsumer(@Value("${acntech.webservice.client.url}") String webServiceUrl) {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(GreetingPortType.class);
        jaxWsProxyFactoryBean.setAddress(webServiceUrl);
        return (GreetingPortType) jaxWsProxyFactoryBean.create();
    }
}

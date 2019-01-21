package no.acntech.sandbox.config;

import no.acntech.sandbox.webservice.endpoint.SimpleWebService;
import no.acntech.sandbox.webservice.simple.v1_0.SimplePortType;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class SpringWebserviceConfig {

    private final Bus bus;

    @Autowired
    public SpringWebserviceConfig(final Bus bus) {
        this.bus = bus;
    }

    @Autowired
    @Bean
    public Endpoint simpleEndpoint(final SimpleWebService simpleWebService, @Value("${simple.webservice.path}") String simpleWebServicePath) {
        EndpointImpl endpoint = new EndpointImpl(bus, simpleWebService);
        endpoint.publish(simpleWebServicePath);
        return endpoint;
    }

    @Bean
    public SimplePortType simpleConsumer(@Value("${simple.webservice.address}") String simpleWebServiceAddress) {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(SimplePortType.class);
        jaxWsProxyFactoryBean.setAddress(simpleWebServiceAddress);
        return (SimplePortType) jaxWsProxyFactoryBean.create();
    }
}

package no.acntech.sandbox.config;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import no.acntech.sandbox.client.GreetingClient;

@Configuration
public class WebServiceConfig {

    @Bean
    public GreetingClient greetingConsumer(@Value("${acntech.webservice.url}") String webServiceUrl) {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(GreetingClient.class);
        jaxWsProxyFactoryBean.setAddress(webServiceUrl);
        return (GreetingClient) jaxWsProxyFactoryBean.create();
    }
}

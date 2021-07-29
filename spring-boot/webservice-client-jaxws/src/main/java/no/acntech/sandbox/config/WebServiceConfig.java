package no.acntech.sandbox.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import no.acntech.sandbox.client.GreetingClient;

@Configuration
public class WebServiceConfig {

    @Bean
    public JaxWsPortProxyFactoryBean greetingClient(
            @Value("${acntech.webservice.url}") String webServiceUrl,
            @Value("classpath:wsdl/v1/GreetingService.wsdl") final Resource wsdlResource) throws Exception {
        JaxWsPortProxyFactoryBean proxyFactoryBean = new JaxWsPortProxyFactoryBean();
        proxyFactoryBean.setEndpointAddress(webServiceUrl);
        proxyFactoryBean.setWsdlDocumentResource(wsdlResource);
        proxyFactoryBean.setNamespaceUri("urn:no:acntech:sandbox:webservice:greeting:v1");
        proxyFactoryBean.setServiceName("GreetingService");
        proxyFactoryBean.setServiceInterface(GreetingClient.class);
        return proxyFactoryBean;
    }
}

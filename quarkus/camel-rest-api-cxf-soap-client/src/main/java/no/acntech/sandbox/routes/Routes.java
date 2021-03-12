package no.acntech.sandbox.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.soap.SoapJaxbDataFormat;
import org.apache.camel.dataformat.soap.name.ServiceInterfaceStrategy;

import no.acntech.sandbox.webservice.simple.v1.SimpleService;

public class Routes extends RouteBuilder {

    private static final String CONTEXT_PATH = "no.acntech.sandbox.webservice.simple.v1";

    @Override
    public void configure() throws Exception {
        SoapJaxbDataFormat soapDF = new SoapJaxbDataFormat(CONTEXT_PATH, new ServiceInterfaceStrategy(SimpleService.class, true));

        from("rest:get:/api/greetings")
                .choice()
                .when(simple("${header.CamelHttpMethod} == 'GET'"))
                .setBody(this::handleRequest)
                .endChoice()
                .end()
                .marshal().json()
                .to("")
                .unmarshal(soapDF);
    }

    private Object handleRequest(Exchange exchange) {
        return exchange.getMessage();
    }
}

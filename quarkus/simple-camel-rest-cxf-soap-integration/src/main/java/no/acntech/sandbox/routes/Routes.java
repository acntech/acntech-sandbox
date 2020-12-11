package no.acntech.sandbox.routes;

import no.acntech.sandbox.webservice.simple.v1_0.SimpleService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.soap.SoapJaxbDataFormat;
import org.apache.camel.dataformat.soap.name.ServiceInterfaceStrategy;

public class Routes extends RouteBuilder {

    private static final String CONTEXT_PATH = "no.acntech.sandbox.webservice.simple.v1.0";

    @Override
    public void configure() throws Exception {
        SoapJaxbDataFormat soapDF = new SoapJaxbDataFormat(CONTEXT_PATH, new ServiceInterfaceStrategy(SimpleService.class, true));

        from("platform-http:/api/greetings?httpMethodRestrict=GET")
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
        return exchange.;
    }
}

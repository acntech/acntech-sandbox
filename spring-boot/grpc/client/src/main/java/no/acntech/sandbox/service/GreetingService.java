package no.acntech.sandbox.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import no.acntech.sandbox.model.Greeting;
import no.acntech.sandbox.model.GreetingGrpc;
import no.acntech.sandbox.model.Hello;
import no.acntech.sandbox.model.HelloRequest;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @GrpcClient("grpc-server")
    private GreetingGrpc.GreetingBlockingStub greetingBlockingStub;

    public Greeting getGreeting(Hello hello) {
        var request = HelloRequest.newBuilder()
                .setName(hello.getName())
                .build();
        var response = greetingBlockingStub.sayHello(request);
        return new Greeting(response.getMessage());
    }
}

package no.acntech.sandbox.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import no.acntech.sandbox.model.FormData;
import no.acntech.sandbox.model.GreetingDto;
import no.acntech.sandbox.model.GreetingGrpc;
import no.acntech.sandbox.model.HelloRequest;

@Service
public class GreetingService {

    @GrpcClient("grpc-server")
    private GreetingGrpc.GreetingBlockingStub greetingBlockingStub;

    public GreetingDto getGreeting(FormData formData) {
        var request = HelloRequest.newBuilder()
                .setName(formData.getName())
                .build();
        var response = greetingBlockingStub.sayHello(request);
        return new GreetingDto(response.getMessage());
    }
}

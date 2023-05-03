package no.acntech.sandbox.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import no.acntech.sandbox.model.GreetingGrpc;
import no.acntech.sandbox.model.HelloRequest;
import no.acntech.sandbox.model.HelloResponse;

@GrpcService
public class GreetingService extends GreetingGrpc.GreetingImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        var response = HelloResponse.newBuilder()
                .setMessage("Hello " + request.getName() + "!")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}


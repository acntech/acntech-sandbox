package no.acntech.sandbox.service

import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import no.acntech.sandbox.model.GreetingGrpc
import no.acntech.sandbox.model.HelloRequest
import no.acntech.sandbox.model.HelloResponse

@GrpcService
class GreetingService : GreetingGrpc.GreetingImplBase() {

    override fun sayHello(request: HelloRequest, responseObserver: StreamObserver<HelloResponse?>) {
        val response = HelloResponse.newBuilder()
                .setMessage("Hello ${request.name}!")
                .build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}
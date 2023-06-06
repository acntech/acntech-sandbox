package no.acntech.sandbox.service

import net.devh.boot.grpc.client.inject.GrpcClient
import no.acntech.sandbox.model.Greeting
import no.acntech.sandbox.model.GreetingGrpc
import no.acntech.sandbox.model.Hello
import no.acntech.sandbox.model.HelloRequest
import org.springframework.stereotype.Service

@Service
class GreetingService {

    @GrpcClient("grpc-server")
    lateinit var greetingBlockingStub: GreetingGrpc.GreetingBlockingStub

    fun getGreeting(hello: Hello): Greeting {
        val request = HelloRequest.newBuilder()
                .setName(hello.name)
                .build()
        val response = greetingBlockingStub.sayHello(request)
        return Greeting(response.message)
    }
}
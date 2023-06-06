package no.acntech.sandbox.config

import net.devh.boot.grpc.common.autoconfigure.GrpcCommonCodecAutoConfiguration
import net.devh.boot.grpc.common.autoconfigure.GrpcCommonTraceAutoConfiguration
import net.devh.boot.grpc.server.autoconfigure.*
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.context.annotation.Configuration

/**
 * This configuration is needed due to the grpc-client-spring-boot-starter is not compatible with Spring Boot 3.
 * See this GitHub issue: <a href="https://github.com/yidongnan/grpc-spring-boot-starter/issues/778">#778</a>
 */
@ImportAutoConfiguration(
        GrpcCommonCodecAutoConfiguration::class,
        GrpcCommonTraceAutoConfiguration::class,
        GrpcAdviceAutoConfiguration::class,
        GrpcHealthServiceAutoConfiguration::class,
        GrpcServerAutoConfiguration::class,
        GrpcServerFactoryAutoConfiguration::class,
        GrpcServerMetricAutoConfiguration::class,
        GrpcServerSecurityAutoConfiguration::class,
        GrpcServerTraceAutoConfiguration::class
)
@Configuration(proxyBeanMethods = false)
class GrpcConfig
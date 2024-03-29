package no.acntech.sandbox.config

import net.devh.boot.grpc.client.autoconfigure.*
import net.devh.boot.grpc.common.autoconfigure.GrpcCommonCodecAutoConfiguration
import net.devh.boot.grpc.common.autoconfigure.GrpcCommonTraceAutoConfiguration
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.context.annotation.Configuration

/**
 * This configuration is needed due to the grpc-client-spring-boot-starter is not compatible with Spring Boot 3.
 * See this GitHub issue: <a href="https://github.com/yidongnan/grpc-spring-boot-starter/issues/778">#778</a>
 */
@ImportAutoConfiguration(
        GrpcClientAutoConfiguration::class,
        GrpcClientMetricAutoConfiguration::class,
        GrpcClientHealthAutoConfiguration::class,
        GrpcClientSecurityAutoConfiguration::class,
        GrpcClientTraceAutoConfiguration::class,
        GrpcDiscoveryClientAutoConfiguration::class,
        GrpcCommonCodecAutoConfiguration::class,
        GrpcCommonTraceAutoConfiguration::class
)
@Configuration(proxyBeanMethods = false)
class GrpcConfig
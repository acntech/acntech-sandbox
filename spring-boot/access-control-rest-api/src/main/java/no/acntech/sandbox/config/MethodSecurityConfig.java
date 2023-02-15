package no.acntech.sandbox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@Configuration(proxyBeanMethods = false)
public class MethodSecurityConfig {
}

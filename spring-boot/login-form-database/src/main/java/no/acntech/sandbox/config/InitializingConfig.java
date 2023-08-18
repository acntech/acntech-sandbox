package no.acntech.sandbox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;

import no.acntech.sandbox.model.RoleEntity;
import no.acntech.sandbox.model.UserEntity;
import no.acntech.sandbox.repository.RoleRepository;
import no.acntech.sandbox.repository.UserRepository;

@Configuration(proxyBeanMethods = false)
public class InitializingConfig {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public InitializingConfig(final PasswordEncoder passwordEncoder,
                              final RoleRepository roleRepository,
                              final UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initialize() {
        final var userRole = RoleEntity.builder()
                .role("USER")
                .description("Basic user")
                .build();
        final var savedUserRole = roleRepository.save(userRole);
        final var adminRole = RoleEntity.builder()
                .role("ADMIN")
                .description("Administrator")
                .build();
        final var savedAdminRole = roleRepository.save(adminRole);
        final var user = UserEntity.builder()
                .username("user")
                .firstName("The")
                .lastName("User")
                .password(passwordEncoder.encode("user"))
                .email("user@acntech.no")
                .roles(Collections.singletonList(savedUserRole))
                .build();
        userRepository.save(user);
        final var admin = UserEntity.builder()
                .username("admin")
                .firstName("The")
                .lastName("Admin")
                .password(passwordEncoder.encode("admin"))
                .email("admin@acntech.no")
                .roles(Arrays.asList(savedUserRole, savedAdminRole))
                .build();
        userRepository.save(admin);
    }
}

package no.acntech.sandbox.config;

import jakarta.annotation.PostConstruct;
import no.acntech.sandbox.model.Role;
import no.acntech.sandbox.model.User;
import no.acntech.sandbox.repository.RoleRepository;
import no.acntech.sandbox.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;

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
        Role userRole = Role.builder()
                .role("USER")
                .description("Basic user")
                .build();
        Role savedUserRole = roleRepository.save(userRole);
        Role adminRole = Role.builder()
                .role("ADMIN")
                .description("Administrator")
                .build();
        Role savedAdminRole = roleRepository.save(adminRole);

        User user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .email("user@email.com")
                .roles(Collections.singletonList(savedUserRole))
                .build();
        userRepository.save(user);
        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .email("admin@email.com")
                .roles(Arrays.asList(savedUserRole, savedAdminRole))
                .build();
        userRepository.save(admin);
    }
}

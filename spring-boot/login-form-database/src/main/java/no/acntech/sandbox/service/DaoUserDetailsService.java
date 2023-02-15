package no.acntech.sandbox.service;

import no.acntech.sandbox.domain.DefaultUserDetails;
import no.acntech.sandbox.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DaoUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DaoUserDetailsService.class);
    private final UserRepository userRepository;

    public DaoUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Retrieving user information for {}", username);
        final var user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found for username " + username));
        final var userDetails = DefaultUserDetails.builder(user).build();
        LOGGER.info("Created security user:\n\t{}\n\t{}\n\t{}", userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        return userDetails;
    }
}

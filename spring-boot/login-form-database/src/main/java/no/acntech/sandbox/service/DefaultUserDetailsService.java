package no.acntech.sandbox.service;

import no.acntech.sandbox.domain.DefaultUserDetails;
import no.acntech.sandbox.domain.User;
import no.acntech.sandbox.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserDetailsService.class);

    private final UserRepository userRepository;

    public DefaultUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Retrieving user information for {}", username);

        Optional<User> optionalUser = userRepository.findById(username);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("No user found for username " + username));
        DefaultUserDetails userDetails = DefaultUserDetails.builder(user).build();

        LOGGER.info("Created security user:\n\t{}\n\t{}\n\t{}", userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        return userDetails;
    }
}

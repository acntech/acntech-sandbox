package no.acntech.sandbox.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import no.acntech.sandbox.repository.UserRepository;

@Service
public class DaoUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DaoUserDetailsService.class);
    private final ConversionService conversionService;
    private final UserRepository userRepository;

    public DaoUserDetailsService(final ConversionService conversionService,
                                 final UserRepository userRepository) {
        this.conversionService = conversionService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull final String username) throws UsernameNotFoundException {
        LOGGER.info("Retrieving user information for {}", username);
        final var user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found for username " + username));
        final var userDetails = conversionService.convert(user, UserDetails.class);
        Assert.notNull(userDetails, "Could not convert user");
        LOGGER.info("Loaded security user:\n{}", userDetails);
        return userDetails;
    }
}

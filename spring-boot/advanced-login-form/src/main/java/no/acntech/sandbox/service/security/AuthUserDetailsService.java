package no.acntech.sandbox.service.security;

import no.acntech.sandbox.domain.security.AuthUser;
import no.acntech.sandbox.entity.user.User;
import no.acntech.sandbox.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthUserDetailsService.class);

    private final UserRepository userRepository;

    @Autowired
    public AuthUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Retrieving user information for {}", username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for username " + username);
        } else {
            List<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                    .collect(Collectors.toList());

            LOGGER.info("Creating security user:\n\t{}\n\t{}\n\t{}\n\t{}", user.getUsername(), user.getPassword(), user.getSalt(), authorities);

            return new AuthUser(user.getUsername(), user.getPassword(), user.getSalt(), authorities);
        }
    }
}

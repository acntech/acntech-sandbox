package no.acntech.sandbox.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import no.acntech.sandbox.model.RoleEntity;
import no.acntech.sandbox.model.UserEntity;

@Component
public class UserEntityToUserDetailsConverter implements Converter<UserEntity, UserDetails> {

    @Nullable
    @Override
    public UserDetails convert(@Nullable final UserEntity source) {
        if (source == null) {
            return null;
        }
        final var authorities = source.getRoles().stream()
                .map(this::convert)
                .toList();
        return new User(source.getUsername(), source.getPassword(), authorities);
    }

    private GrantedAuthority convert(final RoleEntity source) {
        return new SimpleGrantedAuthority("ROLE_".concat(source.getRole()));
    }
}

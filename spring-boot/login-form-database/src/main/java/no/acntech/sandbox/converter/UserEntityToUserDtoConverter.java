package no.acntech.sandbox.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import no.acntech.sandbox.model.RoleEntity;
import no.acntech.sandbox.model.UserDto;
import no.acntech.sandbox.model.UserEntity;

@Component
public class UserEntityToUserDtoConverter implements Converter<UserEntity, UserDto> {

    @Nullable
    @Override
    public UserDto convert(@Nullable final UserEntity source) {
        if (source == null) {
            return null;
        }
        return new UserDto(
                source.getUsername(),
                source.getFirstName(),
                source.getLastName(),
                source.getEmail(),
                source.getRoles().stream()
                        .map(RoleEntity::getRole)
                        .collect(Collectors.toList())
        );
    }
}

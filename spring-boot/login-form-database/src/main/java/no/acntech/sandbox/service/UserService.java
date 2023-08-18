package no.acntech.sandbox.service;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import no.acntech.sandbox.model.UserDto;
import no.acntech.sandbox.model.UserEntity;
import no.acntech.sandbox.repository.UserRepository;

@Service
public class UserService {

    private final ConversionService conversionService;
    private final UserRepository userRepository;

    public UserService(final ConversionService conversionService,
                       final UserRepository userRepository) {
        this.conversionService = conversionService;
        this.userRepository = userRepository;
    }

    public List<UserDto> findUsers() {
        return userRepository.findAll().stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }

    private UserDto toUserDto(final UserEntity source) {
        return conversionService.convert(source, UserDto.class);
    }
}

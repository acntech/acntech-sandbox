package no.acntech.prototype.util.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence plaintextPassword) {
        return null;
    }

    @Override
    public boolean matches(CharSequence plaintextPassword, String encodedPassword) {
        return false;
    }
}

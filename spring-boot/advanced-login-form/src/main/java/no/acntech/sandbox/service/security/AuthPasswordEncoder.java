package no.acntech.sandbox.service.security;

import no.acntech.sandbox.util.security.Password;
import no.acntech.sandbox.util.security.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthPasswordEncoder extends PasswordEncoder {

    @Override
    public String encodePassword(String plaintextPassword, Object salt) {
        if (plaintextPassword == null || salt == null) {
            return null;
        }
        Password password = SecurityUtils.encryptPassword(plaintextPassword, salt.toString());
        return password.getHashedPassword();
    }

    @Override
    public boolean isPasswordValid(String hashedPassword, String plaintextPassword, Object salt) {
        if (hashedPassword == null || plaintextPassword == null || salt == null) {
            return false;
        }
        Password password = new Password(hashedPassword, salt.toString());
        return SecurityUtils.verifyPassword(plaintextPassword, password);
    }
}

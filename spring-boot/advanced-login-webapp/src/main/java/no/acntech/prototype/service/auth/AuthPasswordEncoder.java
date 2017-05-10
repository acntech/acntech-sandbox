package no.acntech.prototype.service.auth;

import no.acntech.prototype.util.security.Password;
import no.acntech.prototype.util.security.SecurityUtils;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;

public class AuthPasswordEncoder extends BasePasswordEncoder {

    @Override
    public String encodePassword(String plaintextPassword, Object salt) {
        if (plaintextPassword == null || salt == null) {
            return null;
        }
        Password password = encryptPassword(plaintextPassword, salt);
        return password.getHashedPassword();
    }

    @Override
    public boolean isPasswordValid(String hashedPassword, String plaintextPassword, Object salt) {
        if (hashedPassword == null || plaintextPassword == null || salt == null) {
            return false;
        }
        Password password = encryptPassword(plaintextPassword, salt);
        return hashedPassword.equals(password.getHashedPassword());
    }

    private Password encryptPassword(String plaintextPassword, Object salt) {
        if (plaintextPassword == null) {
            throw new IllegalArgumentException("Input plaintext password is null");
        }
        if (salt == null) {
            throw new IllegalArgumentException("Input salt is null");
        }
        return SecurityUtils.encryptPassword(plaintextPassword, salt.toString());
    }
}

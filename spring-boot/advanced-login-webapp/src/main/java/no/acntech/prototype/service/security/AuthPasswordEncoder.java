package no.acntech.prototype.service.security;

import no.acntech.prototype.util.security.Password;
import no.acntech.prototype.util.security.SecurityUtils;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;

public class AuthPasswordEncoder extends BasePasswordEncoder {

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

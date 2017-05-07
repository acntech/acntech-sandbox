package no.acntech.prototype.util.security;

import org.junit.Test;


public class SecurityUtilsTest {

    @Test
    public void test() throws Exception {
        Password password = SecurityUtils.encryptPassword("admin");
        System.out.println(password.getHashedPassword());
        System.out.println(password.getSalt());

        System.out.println(SecurityUtils.verifyPassword("admin", password));
    }
}
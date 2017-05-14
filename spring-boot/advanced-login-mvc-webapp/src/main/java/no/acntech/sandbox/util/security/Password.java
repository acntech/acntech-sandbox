package no.acntech.sandbox.util.security;

public class Password {

    private String hashedPassword;
    private String salt;

    public Password(String hashedPassword, String salt) {
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Password) {
            Password password = (Password) obj;
            return password.getHashedPassword() == null ?
                    this.hashedPassword == null : password.getHashedPassword().equals(this.hashedPassword);
        }
        return false;
    }
}

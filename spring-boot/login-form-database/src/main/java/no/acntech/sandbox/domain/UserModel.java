package no.acntech.sandbox.domain;

import java.io.Serializable;

public class UserModel implements Serializable {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String[] roles;

    private UserModel() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String[] getRoles() {
        return roles;
    }

    public static Builder builder(final User user) {
        Builder builder = new Builder();
        builder.username = user.getUsername();
        builder.password = user.getPassword();
        builder.firstName = user.getFirstName();
        builder.lastName = user.getLastName();
        builder.email = user.getEmail();
        builder.roles = user.getRoles().stream()
                .map(Role::getRole)
                .toArray(String[]::new);
        return builder;
    }

    public static final class Builder {

        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private String[] roles;

        private Builder() {
        }

        public UserModel build() {
            UserModel user = new UserModel();
            user.firstName = this.firstName;
            user.email = this.email;
            user.password = this.password;
            user.username = this.username;
            user.lastName = this.lastName;
            user.roles = this.roles;
            return user;
        }
    }
}

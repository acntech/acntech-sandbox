package no.acntech.sandbox.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "USERS")
@Entity
public class User implements Serializable {

    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "ROLE"))
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

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

    public List<Role> getRoles() {
        return roles;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private List<Role> roles;

        private Builder() {
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder roles(List<Role> roles) {
            this.roles = roles;
            return this;
        }

        public User build() {
            User user = new User();
            user.roles = this.roles;
            user.firstName = this.firstName;
            user.email = this.email;
            user.password = this.password;
            user.username = this.username;
            user.lastName = this.lastName;
            return user;
        }
    }
}

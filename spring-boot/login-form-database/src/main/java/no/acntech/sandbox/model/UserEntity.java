package no.acntech.sandbox.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Table(name = "USERS")
@Entity
public class UserEntity implements Serializable {

    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "ROLE"))
    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> roles;

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

    public List<RoleEntity> getRoles() {
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
        private List<RoleEntity> roles;

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

        public Builder roles(List<RoleEntity> roles) {
            this.roles = roles;
            return this;
        }

        public UserEntity build() {
            final var target = new UserEntity();
            target.roles = this.roles;
            target.firstName = this.firstName;
            target.email = this.email;
            target.password = this.password;
            target.username = this.username;
            target.lastName = this.lastName;
            return target;
        }
    }
}

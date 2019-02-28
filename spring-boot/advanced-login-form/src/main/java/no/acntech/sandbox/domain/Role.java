package no.acntech.sandbox.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "ROLES")
@Entity
public class Role implements Serializable {

    @Id
    private String role;
    private String description;

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String role;
        private String description;

        private Builder() {
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Role build() {
            Role role = new Role();
            role.description = this.description;
            role.role = this.role;
            return role;
        }
    }
}

package no.acntech.sandbox.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    private boolean enabled;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;

    private DefaultUserDetails() {
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(final User user) {
        Builder builder = new Builder();
        builder.username = user.getUsername();
        builder.password = user.getPassword();
        builder.authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toList());
        return builder;
    }

    public static final class Builder {

        private String username;
        private String password;
        private List<GrantedAuthority> authorities;

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

        public Builder authorities(List<GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public DefaultUserDetails build() {
            DefaultUserDetails defaultUserDetails = new DefaultUserDetails();
            defaultUserDetails.username = this.username;
            defaultUserDetails.password = this.password;
            defaultUserDetails.authorities = this.authorities;
            defaultUserDetails.enabled = true; // Account enabled by default
            defaultUserDetails.accountNonLocked = true; // Account not locked by default
            defaultUserDetails.accountNonExpired = true; // Account not expired by default
            defaultUserDetails.credentialsNonExpired = true; // Credentials not expired by default
            return defaultUserDetails;
        }
    }
}

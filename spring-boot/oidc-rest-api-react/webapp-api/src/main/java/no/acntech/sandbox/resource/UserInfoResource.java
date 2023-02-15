package no.acntech.sandbox.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api/userinfo")
@RestController
public class UserInfoResource {

    @GetMapping
    public ResponseEntity<Object> getUserInfo(final Authentication authentication) {
        final var principal = authentication.getPrincipal();
        if (principal instanceof OAuth2User oAuth2User) {
            return ResponseEntity.ok(oAuth2User.getAttributes());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

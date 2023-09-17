package no.acntech.sandbox.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api/user")
@RestController
public class UserResource {

    @GetMapping
    public ResponseEntity<Object> getUser(@AuthenticationPrincipal final OAuth2User oAuth2User) {
        return ResponseEntity.ok(oAuth2User.getAttributes());
    }
}

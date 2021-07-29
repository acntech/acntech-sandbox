package no.acntech.sandbox.resource;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import no.acntech.sandbox.service.KeyService;

@RequestMapping(path = "keys")
@RestController
public class JwkResource {

    private final KeyService keyService;

    public JwkResource(final KeyService keyService) {
        this.keyService = keyService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getJwk() throws Exception {
        RSAKey rsaKey = keyService.privateRsaKey();
        JWKSet jwkSet = new JWKSet(rsaKey);
        Map<String, Object> jsonObject = jwkSet.toJSONObject(true);
        return ResponseEntity.ok(jsonObject);
    }
}

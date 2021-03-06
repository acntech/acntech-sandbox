package no.acntech.sandbox.resource;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import net.minidev.json.JSONObject;
import no.acntech.sandbox.config.JwtConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RequestMapping(path = "keys")
@RestController
public class JwkResource {

    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public JwkResource(final RSAPrivateKey privateKey,
                       final RSAPublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getJwk() {
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .keyUse(JwtConfig.KEY_USE)
            .algorithm(JwtConfig.ALGORITHM)
            .keyID(JwtConfig.KEY_ID)
            .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        JSONObject jsonObject = jwkSet.toJSONObject(JwtConfig.JWK_PUBLIC_KEYS_ONLY);
        return ResponseEntity.ok(jsonObject);
    }
}

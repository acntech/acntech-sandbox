package no.acntech.sandbox.resource;

import javax.validation.Valid;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import no.acntech.sandbox.domain.Jwt;

@RestController
public class JwtResource {

    private static final String KEY_ALGORITHM = "RSA";
    private static final int KEY_SIZE = 2048;
    private static final JWSAlgorithm ALGORITHM = JWSAlgorithm.RS256;
    private static final String ISSUER = "http://acntech.no";
    private static final String HEADER_CONTENT_TYPE = "JWT";
    private static final String KEY_ID = UUID.randomUUID().toString();
    private static final KeyPair KEY_PAIR = JwtResource.generateKeyPair();
    private static final RSAPublicKey PUBLIC_KEY = (RSAPublicKey) KEY_PAIR.getPublic();
    private static final RSAPrivateKey PRIVATE_KEY = (RSAPrivateKey) KEY_PAIR.getPrivate();

    private static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            generator.initialize(KEY_SIZE);
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/jwt")
    public ResponseEntity<Map<String, Object>> getJwt() throws Exception {
        RSAKey senderJWK = new RSAKey.Builder(PUBLIC_KEY)
                .privateKey(PRIVATE_KEY)
                .algorithm(ALGORITHM)
                .keyUse(KeyUse.SIGNATURE)
                .keyID(KEY_ID)
                .build();

        RSASSASigner signer = new RSASSASigner(senderJWK);

        JWSHeader jwsHeader = new JWSHeader.Builder(ALGORITHM)
                .contentType(HEADER_CONTENT_TYPE)
                .keyID(senderJWK.getKeyID())
                .build();

        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .subject("acntech")
                .issueTime(new Date())
                .issuer(ISSUER)
                .build();

        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaims);
        signedJWT.sign(signer);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("jwt", signedJWT.serialize());
        response.put("pub", Base64.getEncoder().encodeToString(senderJWK.toPublicKey().getEncoded()));
        response.put("priv", Base64.getEncoder().encodeToString(senderJWK.toPrivateKey().getEncoded()));
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/jwt")
    public ResponseEntity<Map<String, Object>> postJwt(@RequestBody @Valid Jwt jwt) throws Exception {
        RSAKey recipientJWK = new RSAKey.Builder(PUBLIC_KEY)
                .keyUse(KeyUse.SIGNATURE)
                .keyID(KEY_ID)
                .build();

        JWSVerifier verifier = new RSASSAVerifier(recipientJWK);

        SignedJWT signedJWT = SignedJWT.parse(jwt.getJwt());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("jwt", jwt.getJwt());
        response.put("verified", signedJWT.verify(verifier));
        response.put("pub", Base64.getEncoder().encodeToString(recipientJWK.toPublicKey().getEncoded()));
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/jwk")
    public ResponseEntity<Map<String, Object>> getJwk() {
        RSAKey jwk = new RSAKey.Builder(PUBLIC_KEY)
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(ALGORITHM)
                .keyID(KEY_ID)
                .build();

        List<Object> keys = Collections.singletonList(jwk.toJSONObject());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("keys", keys);
        return ResponseEntity.ok(response);
    }
}

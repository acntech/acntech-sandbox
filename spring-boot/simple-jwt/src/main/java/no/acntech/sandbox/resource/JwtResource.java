package no.acntech.sandbox.resource;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import no.acntech.sandbox.config.JwtConfig;
import no.acntech.sandbox.domain.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@RequestMapping(path = "token")
@RestController
public class JwtResource {

    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public JwtResource(final RSAPrivateKey privateKey,
                       final RSAPublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    @GetMapping
    public ResponseEntity<Jwt> getJwt() throws Exception {
        RSAKey senderJWK = new RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .algorithm(JwtConfig.ALGORITHM)
            .keyUse(JwtConfig.KEY_USE)
            .keyID(JwtConfig.KEY_ID)
            .build();

        RSASSASigner signer = new RSASSASigner(senderJWK);

        JWSHeader jwsHeader = new JWSHeader.Builder(JwtConfig.ALGORITHM)
            .contentType(JwtConfig.HEADER_CONTENT_TYPE)
            .keyID(senderJWK.getKeyID())
            .build();

        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
            .subject("acntech")
            .issueTime(new Date())
            .issuer(JwtConfig.ISSUER)
            .build();

        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaims);
        signedJWT.sign(signer);

        Jwt jwt = new Jwt();
        jwt.setJwt(signedJWT.serialize());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping
    public ResponseEntity<Jwt> postJwt(@RequestBody @Valid Jwt jwt) throws Exception {
        RSAKey recipientJWK = new RSAKey.Builder(publicKey)
            .keyUse(JwtConfig.KEY_USE)
            .keyID(JwtConfig.KEY_ID)
            .build();

        JWSVerifier verifier = new RSASSAVerifier(recipientJWK);

        SignedJWT signedJWT = SignedJWT.parse(jwt.getJwt());

        jwt.setVerified(signedJWT.verify(verifier));

        return ResponseEntity.ok(jwt);
    }
}

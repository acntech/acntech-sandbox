package no.acntech.sandbox.resource;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.validation.Valid;
import no.acntech.sandbox.domain.Jwt;
import no.acntech.sandbox.service.KeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping(path = "token")
@RestController
public class JwtResource {

    private final KeyService keyService;

    public JwtResource(final KeyService keyService) {
        this.keyService = keyService;
    }

    @GetMapping
    public ResponseEntity<Jwt> getJwt() throws Exception {
        RSAKey senderJWK = keyService.privateRsaKey();

        RSASSASigner signer = new RSASSASigner(senderJWK);

        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .contentType("JWT")
                .keyID(senderJWK.getKeyID())
                .build();

        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .subject("acntech")
                .issueTime(new Date())
                .issuer("http:/localhost:8080")
                .build();

        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaims);
        signedJWT.sign(signer);

        Jwt jwt = new Jwt();
        jwt.setJwt(signedJWT.serialize());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping
    public ResponseEntity<Jwt> postJwt(@RequestBody @Valid Jwt jwt) throws Exception {
        RSAKey recipientJWK = keyService.publicRsaKey();

        JWSVerifier verifier = new RSASSAVerifier(recipientJWK);

        SignedJWT signedJWT = SignedJWT.parse(jwt.getJwt());

        jwt.setVerified(signedJWT.verify(verifier));

        return ResponseEntity.ok(jwt);
    }
}

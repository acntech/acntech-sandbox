package no.acntech.sandbox.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.KeyUse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

@Configuration
public class JwtConfig {

    private static final String KEY_ALGORITHM = "RSA";
    public static final JWSAlgorithm ALGORITHM = JWSAlgorithm.RS256;
    public static final KeyUse KEY_USE = KeyUse.SIGNATURE;
    public static final String ISSUER = "http://acntech.no";
    public static final String HEADER_CONTENT_TYPE = "JWT";
    public static final String KEY_ID = UUID.randomUUID().toString();
    public static final boolean JWK_PUBLIC_KEYS_ONLY = false;
    private final KeyFactory keyFactory;

    public JwtConfig() throws Exception {
        this.keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    }

    @Bean
    public RSAPrivateKey privateKey(@Value("classpath:private.pem") final Resource privateKeyFile) throws Exception {
        byte[] privateKeyFileBytes = Files.readAllBytes(privateKeyFile.getFile().toPath());
        String privateKey = new String(privateKeyFileBytes)
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replaceAll("\\s", "");
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey);
        return (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

    }

    @Bean
    public RSAPublicKey publicKey(@Value("classpath:public.pem") final Resource publicKeyFile) throws Exception {
        byte[] publicKeyFileBytes = Files.readAllBytes(publicKeyFile.getFile().toPath());
        String publicKey = new String(publicKeyFileBytes)
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replaceAll("\\s", "");
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
        return (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
    }
}

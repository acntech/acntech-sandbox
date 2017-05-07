package no.acntech.prototype.util.security;

import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Base64;

public final class SecurityUtils2 {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static final int CRYPTO_KEY_LENGTH = 512;
    private static final int CRYPTO_SALT_LENGTH = 64;
    private static final String CRYPTO_ALGORITHM = "SHA-512";
    private static final String CRYPTO_PROVIDER = "BC";
    private static final String CRYPTO_MAGIC_BYTES = "G4ND4LF";
    private static final DigestRandomGenerator RANDOM_GENERATOR = new DigestRandomGenerator(new SHA3Digest(CRYPTO_KEY_LENGTH));

    private SecurityUtils2() {
    }

    public static Password encryptPassword(String plaintextPassword) throws NoSuchProviderException, NoSuchAlgorithmException {
        if (plaintextPassword == null) {
            throw new IllegalArgumentException("Input plaintext password is null");
        }
        String salt = generateSalt();
        String combinedString = combine(plaintextPassword, salt);
        String hashedPassword = digest(combinedString);
        return new Password(hashedPassword, salt);
    }

    public static boolean verifyPassword(String plaintextPassword, Password password) throws NoSuchProviderException, NoSuchAlgorithmException {
        if (plaintextPassword == null) {
            throw new IllegalArgumentException("Input plaintext password is null");
        }
        if (password == null) {
            throw new IllegalArgumentException("Input password object is null");
        }
        String combinedString = combine(plaintextPassword, password.getSalt());
        String hashedPassword = digest(combinedString);
        return hashedPassword.equals(password.getHashedPassword());
    }

    private static String combine(String plaintextPassword, String salt) {
        return CRYPTO_MAGIC_BYTES.concat(salt).concat(plaintextPassword);
    }

    private static String digest(String input) throws NoSuchProviderException, NoSuchAlgorithmException {
        if (input == null) {
            throw new IllegalArgumentException("Input string is null");
        }
        byte[] inputBytes = input.getBytes(DEFAULT_CHARSET);
        Security.addProvider(new BouncyCastleProvider());
        byte[] digest = MessageDigest.getInstance(CRYPTO_ALGORITHM, CRYPTO_PROVIDER).digest(inputBytes);
        return Base64.getEncoder().encodeToString(digest);
    }


    private static String generateSalt() {
        byte[] salt = new byte[CRYPTO_SALT_LENGTH];
        RANDOM_GENERATOR.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}

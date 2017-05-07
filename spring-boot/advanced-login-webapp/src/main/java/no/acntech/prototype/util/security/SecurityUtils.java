package no.acntech.prototype.util.security;

import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Base64;

public final class SecurityUtils {

    private static final int CRYPTO_KEY_LENGTH = 512;
    private static final int CRYPTO_SALT_LENGTH = 64;
    private static final int CRYPTO_ITERATION_COUNT = 1337;
    private static final String CRYPTO_MAGIC_BYTES = "G4ND4LF";

    private SecurityUtils() {
    }

    public static Password encryptPassword(String plaintextPassword) throws NoSuchProviderException, NoSuchAlgorithmException {
        byte[] salt = generateSalt();
        String encodedSalt = encode(salt);
        return encryptPassword(plaintextPassword, encodedSalt);
    }

    public static Password encryptPassword(String plaintextPassword, String encodedSalt) throws NoSuchProviderException, NoSuchAlgorithmException {
        byte[] hashedPassword = digest(plaintextPassword, encodedSalt, CRYPTO_KEY_LENGTH, CRYPTO_ITERATION_COUNT);
        String encodedHashedPassword = encode(hashedPassword);
        return new Password(encodedHashedPassword, encodedSalt);
    }

    public static boolean verifyPassword(String plaintextPassword, Password password) throws NoSuchProviderException, NoSuchAlgorithmException {
        if (password == null) {
            throw new IllegalArgumentException("Input password object is null");
        }
        Password passwordToVerify = encryptPassword(plaintextPassword, password.getSalt());
        return passwordToVerify.equals(password);
    }

    private static byte[] digest(String plaintextPassword, String encodedSalt, int keyLength, int iterationCount) throws NoSuchProviderException, NoSuchAlgorithmException {
        if (plaintextPassword == null) {
            throw new IllegalArgumentException("Input plaintext password is null");
        }
        if (encodedSalt == null) {
            throw new IllegalArgumentException("Input salt is null");
        }
        if (keyLength <= 0) {
            throw new IllegalArgumentException("Input key length is zero or negative");
        }
        if (iterationCount < 0) {
            throw new IllegalArgumentException("Input iteration count is negative");
        }
        String combinedPassword = combineWithMagicBytes(plaintextPassword, encodedSalt);
        PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator();
        generator.init(PBEParametersGenerator.PKCS5PasswordToBytes(combinedPassword.toCharArray()), encodedSalt.getBytes(), iterationCount);
        return ((KeyParameter) generator.generateDerivedParameters(keyLength)).getKey();
    }

    private static String combineWithMagicBytes(String plaintextPassword, String encodedSalt) {
        return CRYPTO_MAGIC_BYTES.concat(encodedSalt).concat(plaintextPassword);
    }

    private static String encode(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    private static byte[] generateSalt() {
        SecureRandom rng = new SecureRandom();
        return rng.generateSeed(CRYPTO_SALT_LENGTH);
    }
}

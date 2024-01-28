import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

    public static void main(String[] args) {
        try {
            // Create a KeyGenerator object for AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

            // Initialize the KeyGenerator with a key size (128, 192, or 256 bits)
            keyGenerator.init(128);

            // Generate a SecretKey
            SecretKey secretKey = keyGenerator.generateKey();

            // Print the encoded key as a byte array
            byte[] keyBytes = secretKey.getEncoded();
            System.out.println("Generated AES Key: " + bytesToHex(keyBytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }

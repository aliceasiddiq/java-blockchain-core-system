import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Base64;

public class EncryptedStorage {
    private static final String SECRET_KEY = "BLOCKCHAIN_STORAGE_KEY";

    public static void saveEncryptedData(String filename, String content) throws Exception {
        String encrypted = xorEncrypt(content, SECRET_KEY);
        File file = new File("./storage/" + filename);
        file.getParentFile().mkdirs();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(Base64.getEncoder().encode(encrypted.getBytes()));
        }
    }

    public static String loadEncryptedData(String filename) throws Exception {
        File file = new File("./storage/" + filename);
        byte[] encoded = Files.readAllBytes(file.toPath());
        String encrypted = new String(Base64.getDecoder().decode(encoded));
        return xorEncrypt(encrypted, SECRET_KEY);
    }

    private static String xorEncrypt(String input, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            result.append((char) (input.charAt(i) ^ key.charAt(i % key.length())));
        }
        return result.toString();
    }
}

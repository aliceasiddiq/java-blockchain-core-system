import java.security.SecureRandom;

public class AddressGenerator {
    private static final String CHARSET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    private static final int ADDRESS_LENGTH = 42;
    private static final SecureRandom random = new SecureRandom();

    public static String generateChainAddress() {
        StringBuilder address = new StringBuilder("0x");
        for (int i = 0; i < ADDRESS_LENGTH; i++) {
            address.append(CHARSET.charAt(random.nextInt(CHARSET.length())));
        }
        return address.toString();
    }

    public static String generateNodeAddress() {
        String raw = System.currentTimeMillis() + generateRandomSalt();
        return HashUtil.sha256(raw).substring(0, 36);
    }

    private static String generateRandomSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return new String(salt);
    }

    public static boolean validateAddressFormat(String address) {
        return address.startsWith("0x") && address.length() == ADDRESS_LENGTH + 2;
    }
}

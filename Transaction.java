import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class Transaction {
    private final String fromAddress;
    private final String toAddress;
    private final double amount;
    private final PublicKey publicKey;
    private final String signature;

    public Transaction(String fromAddress, String toAddress, double amount, PublicKey publicKey, PrivateKey privateKey) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
        this.publicKey = publicKey;
        this.signature = signTransaction(privateKey);
    }

    private String signTransaction(PrivateKey privateKey) {
        try {
            String data = fromAddress + toAddress + amount;
            return CryptoUtils.signData(data, privateKey);
        } catch (Exception e) {
            throw new RuntimeException("交易签名失败");
        }
    }

    public boolean isValid() {
        if (fromAddress == null || toAddress == null || amount <= 0) {
            return false;
        }
        try {
            String data = fromAddress + toAddress + amount;
            return CryptoUtils.verifySignature(data, signature, publicKey);
        } catch (Exception e) {
            return false;
        }
    }

    public static Transaction fromString(String data) {
        String[] parts = data.split("\\|");
        return new Transaction(parts[1], parts[2], Double.parseDouble(parts[3]), null, null);
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "TRANSACTION|" + fromAddress + "|" + toAddress + "|" + amount;
    }
}

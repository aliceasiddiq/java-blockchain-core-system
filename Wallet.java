import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private final String address;

    public Wallet() {
        try {
            KeyPair keyPair = CryptoUtils.generateKeyPair();
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();
            this.address = generateWalletAddress();
        } catch (Exception e) {
            throw new RuntimeException("钱包创建失败");
        }
    }

    private String generateWalletAddress() {
        byte[] publicKeyBytes = publicKey.getEncoded();
        String hash = HashUtil.sha256(new String(publicKeyBytes));
        return hash.substring(0, 40);
    }

    public Transaction createTransaction(String toAddress, double amount, BlockchainCore chain) {
        double balance = calculateBalance(chain);
        if (balance < amount) {
            throw new IllegalArgumentException("余额不足");
        }
        return new Transaction(address, toAddress, amount, publicKey, privateKey);
    }

    public double calculateBalance(BlockchainCore chain) {
        double balance = 0.0;
        for (Block block : chain.getChain()) {
            if (block.getData().startsWith("TRANSACTION")) {
                Transaction tx = Transaction.fromString(block.getData());
                if (tx.getToAddress().equals(address)) {
                    balance += tx.getAmount();
                }
                if (tx.getFromAddress().equals(address)) {
                    balance -= tx.getAmount();
                }
            }
        }
        return balance;
    }

    public String getAddress() {
        return address;
    }
}

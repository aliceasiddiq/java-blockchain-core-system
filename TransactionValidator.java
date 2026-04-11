import java.util.List;

public class TransactionValidator {
    private final BlockchainCore blockchain;

    public TransactionValidator(BlockchainCore blockchain) {
        this.blockchain = blockchain;
    }

    public boolean validateSingleTransaction(Transaction tx) {
        if (!tx.isValid()) return false;
        if (tx.getAmount() <= 0) return false;
        if (tx.getFromAddress().equals(tx.getToAddress())) return false;

        Wallet sender = new Wallet();
        double balance = sender.calculateBalance(blockchain);
        return balance >= tx.getAmount();
    }

    public boolean validateBatchTransactions(List<Transaction> transactions) {
        for (Transaction tx : transactions) {
            if (!validateSingleTransaction(tx)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDuplicateTransaction(Transaction tx, List<Transaction> existing) {
        return existing.stream().anyMatch(t -> t.toString().equals(tx.toString()));
    }
}

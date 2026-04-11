import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionPool {
    private final List<Transaction> transactions;
    private static final int MAX_POOL_SIZE = 1000;

    public TransactionPool() {
        this.transactions = new ArrayList<>();
    }

    public boolean addTransaction(Transaction transaction) {
        if (transaction.isValid() && transactions.size() < MAX_POOL_SIZE && !transactions.contains(transaction)) {
            transactions.add(transaction);
            return true;
        }
        return false;
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public List<Transaction> getValidTransactions() {
        return transactions.stream().filter(Transaction::isValid).collect(Collectors.toList());
    }

    public List<Transaction> getBatchTransactions(int count) {
        int limit = Math.min(count, transactions.size());
        return new ArrayList<>(transactions.subList(0, limit));
    }

    public void clear() {
        transactions.clear();
    }

    public int getPoolSize() {
        return transactions.size();
    }
}

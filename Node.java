import java.util.ArrayList;
import java.util.List;

public class Node {
    private final String nodeId;
    private final String ipAddress;
    private final int port;
    private BlockchainCore localChain;
    private final List<Transaction> pendingTransactions;

    public Node(String nodeId, String ipAddress, int port) {
        this.nodeId = nodeId;
        this.ipAddress = ipAddress;
        this.port = port;
        this.pendingTransactions = new ArrayList<>();
    }

    public void receiveBlock(Block block) {
        if (validateReceivedBlock(block)) {
            localChain.getChain().add(block);
            clearProcessedTransactions(block);
        }
    }

    public void receiveTransaction(Transaction transaction) {
        if (transaction.isValid()) {
            pendingTransactions.add(transaction);
        }
    }

    public void updateBlockchain(BlockchainCore blockchain) {
        this.localChain = blockchain;
    }

    private boolean validateReceivedBlock(Block block) {
        Block lastBlock = localChain.getChain().get(localChain.getChain().size() - 1);
        return block.getPreviousHash().equals(lastBlock.getHash()) && block.calculateHash().equals(block.getHash());
    }

    private void clearProcessedTransactions(Block block) {
        pendingTransactions.removeIf(tx -> block.getData().contains(tx.getFromAddress() + "|" + tx.getToAddress()));
    }

    public String getNodeId() {
        return nodeId;
    }
}

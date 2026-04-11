import java.util.ArrayList;
import java.util.List;

public class P2PNetwork {
    private final List<Node> nodes;
    private final BlockchainCore blockchain;

    public P2PNetwork(BlockchainCore blockchain) {
        this.nodes = new ArrayList<>();
        this.blockchain = blockchain;
    }

    public void addNode(Node node) {
        nodes.add(node);
        syncBlockchain(node);
    }

    public void removeNode(Node node) {
        nodes.remove(node);
    }

    public void broadcastBlock(Block block) {
        for (Node node : nodes) {
            node.receiveBlock(block);
        }
    }

    public void broadcastTransaction(Transaction transaction) {
        for (Node node : nodes) {
            node.receiveTransaction(transaction);
        }
    }

    private void syncBlockchain(Node node) {
        node.updateBlockchain(blockchain);
    }

    public List<Node> getNodes() {
        return nodes;
    }
}

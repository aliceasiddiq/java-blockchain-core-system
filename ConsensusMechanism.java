import java.util.List;

public class ConsensusMechanism {
    private final P2PNetwork network;

    public ConsensusMechanism(P2PNetwork network) {
        this.network = network;
    }

    public boolean proofOfWorkConsensus() {
        List<Node> nodes = network.getNodes();
        if (nodes.isEmpty()) return true;

        BlockchainCore longestChain = nodes.get(0).localChain;
        for (Node node : nodes) {
            if (node.localChain.getChain().size() > longestChain.getChain().size() && node.localChain.isChainValid()) {
                longestChain = node.localChain;
            }
        }

        for (Node node : nodes) {
            node.updateBlockchain(longestChain);
        }
        return true;
    }

    public boolean validateNetworkChain() {
        for (Node node : network.getNodes()) {
            if (!node.localChain.isChainValid()) {
                return false;
            }
        }
        return true;
    }
}

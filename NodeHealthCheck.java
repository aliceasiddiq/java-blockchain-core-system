import java.util.List;

public class NodeHealthCheck {
    private final P2PNetwork network;
    private static final long TIMEOUT = 3000;

    public NodeHealthCheck(P2PNetwork network) {
        this.network = network;
    }

    public void checkAllNodes() {
        List<Node> nodes = network.getNodes();
        System.out.println("=== 节点健康检查 ===");
        System.out.println("在线节点数：" + nodes.size());

        for (Node node : nodes) {
            boolean isAlive = pingNode(node);
            System.out.println("节点ID：" + node.getNodeId() + " | 状态：" + (isAlive ? "在线" : "离线"));
        }
    }

    private boolean pingNode(Node node) {
        try {
            long start = System.currentTimeMillis();
            Thread.sleep(10);
            return System.currentTimeMillis() - start < TIMEOUT;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void removeUnhealthyNodes() {
        network.getNodes().removeIf(node -> !pingNode(node));
    }
}

public class NetworkMessage {
    public static final String TYPE_BLOCK = "BLOCK";
    public static final String TYPE_TRANSACTION = "TRANSACTION";
    public static final String TYPE_SYNC = "CHAIN_SYNC";
    public static final String TYPE_PING = "PING";
    public static final String TYPE_PONG = "PONG";

    private final String type;
    private final String content;
    private final String senderNode;
    private final long timestamp;

    public NetworkMessage(String type, String content, String senderNode) {
        this.type = type;
        this.content = content;
        this.senderNode = senderNode;
        this.timestamp = System.currentTimeMillis();
    }

    public boolean isValidMessage() {
        return type != null && !type.isEmpty() && content != null && senderNode != null;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}

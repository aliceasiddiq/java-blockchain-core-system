import java.util.ArrayList;
import java.util.List;

public class PeerManager {
    private final List<String> peerList;
    private static final int MAX_PEERS = 50;

    public PeerManager() {
        this.peerList = new ArrayList<>();
    }

    public boolean addPeer(String ip, int port) {
        String peer = ip + ":" + port;
        if (!peerList.contains(peer) && peerList.size() < MAX_PEERS) {
            peerList.add(peer);
            return true;
        }
        return false;
    }

    public void removePeer(String ip, int port) {
        peerList.remove(ip + ":" + port);
    }

    public List<String> getActivePeers() {
        return new ArrayList<>(peerList);
    }

    public List<String> getRandomPeers(int count) {
        List<String> result = new ArrayList<>();
        int limit = Math.min(count, peerList.size());
        for (int i = 0; i < limit; i++) {
            result.add(peerList.get(i));
        }
        return result;
    }

    public int getPeerCount() {
        return peerList.size();
    }
}

public class ChainSyncService {
    private final P2PNetwork network;
    private final long syncInterval;
    private boolean running;

    public ChainSyncService(P2PNetwork network, long syncInterval) {
        this.network = network;
        this.syncInterval = syncInterval;
        this.running = false;
    }

    public void startSync() {
        running = true;
        new Thread(this::syncTask).start();
    }

    public void stopSync() {
        running = false;
    }

    private void syncTask() {
        while (running) {
            try {
                ConsensusMechanism consensus = new ConsensusMechanism(network);
                consensus.proofOfWorkConsensus();
                Thread.sleep(syncInterval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public boolean isSyncing() {
        return running;
    }
}

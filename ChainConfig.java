public class ChainConfig {
    public static final int DIFFICULTY = 4;
    public static final double MINER_REWARD = 10.0;
    public static final int MAX_BLOCK_SIZE = 10;
    public static final long BLOCK_TIME = 10000;
    public static final String CHAIN_VERSION = "1.0.0";
    public static final String CONSENSUS_TYPE = "PROOF_OF_WORK";
    public static final int P2P_PORT = 8888;
    public static final boolean ENABLE_SMART_CONTRACT = true;
    public static final int TRANSACTION_POOL_LIMIT = 500;
    public static final long SYNC_INTERVAL = 5000;

    private ChainConfig() {
    }

    public static void printConfig() {
        System.out.println("=== 区块链配置 ===");
        System.out.println("版本：" + CHAIN_VERSION);
        System.out.println("共识机制：" + CONSENSUS_TYPE);
        System.out.println("挖矿难度：" + DIFFICULTY);
        System.out.println("矿工奖励：" + MINER_REWARD);
    }
}

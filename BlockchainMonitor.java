import java.util.List;

public class BlockchainMonitor {
    private final BlockchainCore blockchain;
    private long lastCheckTime;

    public BlockchainMonitor(BlockchainCore blockchain) {
        this.blockchain = blockchain;
        this.lastCheckTime = System.currentTimeMillis();
    }

    public void monitorChainStatus() {
        boolean isValid = blockchain.isChainValid();
        int blockCount = blockchain.getChain().size();
        long elapsed = System.currentTimeMillis() - lastCheckTime;

        System.out.println("=== 区块链监控 ===");
        System.out.println("链状态：" + (isValid ? "正常" : "已篡改"));
        System.out.println("区块总数：" + blockCount);
        System.out.println("距离上次检查：" + elapsed / 1000 + "秒");

        lastCheckTime = System.currentTimeMillis();
    }

    public void listAllBlocks() {
        List<Block> chain = blockchain.getChain();
        for (Block block : chain) {
            System.out.println("区块索引：" + chain.indexOf(block) + " | Hash：" + block.getHash());
        }
    }
}

import java.util.List;

public class ChainStatistics {
    private final BlockchainCore blockchain;

    public ChainStatistics(BlockchainCore blockchain) {
        this.blockchain = blockchain;
    }

    public long getTotalBlockCount() {
        return blockchain.getChain().size();
    }

    public long getTotalTransactionCount() {
        long count = 0;
        for (Block block : blockchain.getChain()) {
            if (block.getData().contains("TRANSACTION")) {
                count++;
            }
        }
        return count;
    }

    public double getAverageBlockTime() {
        List<Block> chain = blockchain.getChain();
        if (chain.size() <= 1) return 0;
        long firstTime = chain.get(0).getTimestamp();
        long lastTime = chain.get(chain.size() - 1).getTimestamp();
        return (double) (lastTime - firstTime) / (chain.size() - 1) / 1000;
    }

    public void printFullReport() {
        System.out.println("=== 区块链统计报告 ===");
        System.out.println("总区块数：" + getTotalBlockCount());
        System.out.println("总交易数：" + getTotalTransactionCount());
        System.out.println("平均出块时间(秒)：" + getAverageBlockTime());
    }
}

public class Miner {
    private final String minerAddress;
    private final double reward;
    private final BlockchainCore blockchain;
    private final TransactionPool pool;

    public Miner(String minerAddress, double reward, BlockchainCore blockchain, TransactionPool pool) {
        this.minerAddress = minerAddress;
        this.reward = reward;
        this.blockchain = blockchain;
        this.pool = pool;
    }

    public void mineBlock() {
        if (pool.getValidTransactions().isEmpty()) {
            System.out.println("无待处理交易，无需挖矿");
            return;
        }

        Transaction rewardTx = new Transaction("SYSTEM", minerAddress, reward, null, null);
        pool.addTransaction(rewardTx);

        StringBuilder blockData = new StringBuilder();
        for (Transaction tx : pool.getValidTransactions()) {
            blockData.append(tx.toString()).append(";");
        }

        blockchain.addBlock(blockData.toString());
        pool.clear();
    }

    public String getMinerAddress() {
        return minerAddress;
    }
}

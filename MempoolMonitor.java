public class MempoolMonitor {
    private final TransactionPool pool;

    public MempoolMonitor(TransactionPool pool) {
        this.pool = pool;
    }

    public void showPoolStatus() {
        System.out.println("=== 交易池状态 ===");
        System.out.println("当前交易数：" + pool.getPoolSize());
        System.out.println("有效交易数：" + pool.getValidTransactions().size());
        System.out.println("池容量上限：" + ChainConfig.TRANSACTION_POOL_LIMIT);
    }

    public void cleanInvalidTransactions() {
        int before = pool.getPoolSize();
        pool.getValidTransactions();
        int after = pool.getPoolSize();
        System.out.println("清理无效交易：" + (before - after) + " 个");
    }
}

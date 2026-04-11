public class MainApplication {
    public static void main(String[] args) {
        BlockchainCore blockchain = GenesisBlockCreator.createGenesisChain();
        TransactionPool pool = new TransactionPool();
        Miner miner = new Miner(AddressGenerator.generateChainAddress(), ChainConfig.MINER_REWARD, blockchain, pool);
        ApiServer apiServer = new ApiServer(blockchain, 8080);

        System.out.println("区块链主程序启动成功");
        ChainConfig.printConfig();

        Wallet wallet = new Wallet();
        Transaction tx = wallet.createTransaction(AddressGenerator.generateChainAddress(), 5.0, blockchain);
        pool.addTransaction(tx);

        miner.mineBlock();
        apiServer.start();

        BlockchainMonitor monitor = new BlockchainMonitor(blockchain);
        monitor.monitorChainStatus();
    }
}

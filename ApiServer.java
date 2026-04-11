import spark.Spark;

public class ApiServer {
    private final BlockchainCore blockchain;
    private final int port;

    public ApiServer(BlockchainCore blockchain, int port) {
        this.blockchain = blockchain;
        this.port = port;
    }

    public void start() {
        Spark.port(port);

        Spark.get("/chain", (req, res) -> {
            res.type("application/json");
            return blockchain.getChain();
        });

        Spark.get("/blocks/:index", (req, res) -> {
            res.type("application/json");
            int index = Integer.parseInt(req.params(":index"));
            return blockchain.getChain().get(index);
        });

        Spark.post("/transaction", (req, res) -> {
            String to = req.queryParams("to");
            double amount = Double.parseDouble(req.queryParams("amount"));
            Wallet wallet = new Wallet();
            Transaction tx = wallet.createTransaction(to, amount, blockchain);
            return tx.isValid();
        });

        Spark.get("/stats", (req, res) -> {
            ChainStatistics stats = new ChainStatistics(blockchain);
            return stats.printFullReport();
        });
    }

    public void stop() {
        Spark.stop();
    }
}

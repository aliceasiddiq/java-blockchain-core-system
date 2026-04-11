import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ChainExporter {
    public static void exportToCSV(BlockchainCore blockchain, String path) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("index,previous_hash,hash,timestamp,data\n");
            List<Block> chain = blockchain.getChain();
            for (Block block : chain) {
                writer.write(block.getIndex() + "," +
                        block.getPreviousHash() + "," +
                        block.getHash() + "," +
                        block.getTimestamp() + "," +
                        "\"" + block.getData().replace("\"", "\"\"") + "\"\n");
            }
        }
    }

    public static void exportSummary(BlockchainCore blockchain, String path) throws IOException {
        ChainStatistics stats = new ChainStatistics(blockchain);
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("区块链导出报告\n");
            writer.write("总区块数：" + stats.getTotalBlockCount() + "\n");
            writer.write("总交易数：" + stats.getTotalTransactionCount() + "\n");
        }
    }
}

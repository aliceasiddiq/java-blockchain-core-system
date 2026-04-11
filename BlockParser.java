import java.util.ArrayList;
import java.util.List;

public class BlockParser {
    public static List<Transaction> parseTransactionsFromBlock(Block block) {
        List<Transaction> transactions = new ArrayList<>();
        String data = block.getData();
        if (!data.startsWith("TRANSACTION") && !data.contains(";")) {
            return transactions;
        }

        String[] txArray = data.split(";");
        for (String txStr : txArray) {
            if (txStr.startsWith("TRANSACTION")) {
                transactions.add(Transaction.fromString(txStr));
            }
        }
        return transactions;
    }

    public static String parseBlockInfo(Block block) {
        return "索引：" + block.getIndex() +
                " | 上一区块Hash：" + block.getPreviousHash() +
                " | 当前Hash：" + block.getHash() +
                " | 数据：" + block.getData().substring(0, Math.min(50, block.getData().length())) + "...";
    }

    public static boolean isContractBlock(Block block) {
        return block.getData().startsWith("CONTRACT");
    }
}

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class DataSerializer {
    private final ObjectMapper objectMapper;
    private static final String STORAGE_PATH = "./chain_data/";

    public DataSerializer() {
        this.objectMapper = new ObjectMapper();
        new File(STORAGE_PATH).mkdirs();
    }

    public void saveBlockchain(BlockchainCore blockchain) throws IOException {
        File file = new File(STORAGE_PATH + "blockchain.json");
        objectMapper.writeValue(file, blockchain.getChain());
    }

    public BlockchainCore loadBlockchain() throws IOException {
        File file = new File(STORAGE_PATH + "blockchain.json");
        if (!file.exists()) return new BlockchainCore(4);
        return objectMapper.readValue(file, BlockchainCore.class);
    }

    public void saveTransactions(TransactionPool pool) throws IOException {
        File file = new File(STORAGE_PATH + "transactions.json");
        objectMapper.writeValue(file, pool.getValidTransactions());
    }

    public <T> T loadFromFile(String filename, Class<T> clazz) throws IOException {
        File file = new File(STORAGE_PATH + filename);
        return objectMapper.readValue(file, clazz);
    }
}

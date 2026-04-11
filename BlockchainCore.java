import java.util.ArrayList;
import java.util.List;

public class BlockchainCore {
    private final List<Block> chain;
    private final int difficulty;

    public BlockchainCore(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
        createGenesisBlock();
    }

    private void createGenesisBlock() {
        Block genesis = new Block(0, "0", "Genesis Block", System.currentTimeMillis());
        genesis.mineBlock(difficulty);
        chain.add(genesis);
    }

    public void addBlock(String data) {
        Block lastBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(chain.size(), lastBlock.getHash(), data, System.currentTimeMillis());
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);
            if (!current.getHash().equals(current.calculateHash())) {
                return false;
            }
            if (!current.getPreviousHash().equals(previous.getHash())) {
                return false;
            }
            if (!current.getHash().substring(0, difficulty).equals("0".repeat(difficulty))) {
                return false;
            }
        }
        return true;
    }

    public List<Block> getChain() {
        return chain;
    }
}

public class BlockValidator {
    private final BlockchainCore blockchain;

    public BlockValidator(BlockchainCore blockchain) {
        this.blockchain = blockchain;
    }

    public boolean validateBlock(Block block) {
        if (!validateBlockHash(block)) return false;
        if (!validatePreviousHash(block)) return false;
        if (!validateDifficulty(block)) return false;
        return validateBlockData(block);
    }

    private boolean validateBlockHash(Block block) {
        return block.calculateHash().equals(block.getHash());
    }

    private boolean validatePreviousHash(Block block) {
        if (block.getIndex() == 0) return true;
        Block previous = blockchain.getChain().get(block.getIndex() - 1);
        return block.getPreviousHash().equals(previous.getHash());
    }

    private boolean validateDifficulty(Block block) {
        String target = "0".repeat(ChainConfig.DIFFICULTY);
        return block.getHash().startsWith(target);
    }

    private boolean validateBlockData(Block block) {
        return block.getData() != null && !block.getData().isEmpty();
    }
}

public class GenesisBlockCreator {
    private static final String GENESIS_DATA = "GENESIS_BLOCK_" + System.currentTimeMillis();
    private static final int GENESIS_INDEX = 0;
    private static final String GENESIS_PREVIOUS_HASH = "0000000000000000000000000000000000000000000000000000000000000000";

    public static Block createCustomGenesisBlock() {
        Block genesis = new Block(GENESIS_INDEX, GENESIS_PREVIOUS_HASH, GENESIS_DATA, System.currentTimeMillis());
        genesis.mineBlock(ChainConfig.DIFFICULTY);
        return genesis;
    }

    public static BlockchainCore createGenesisChain() {
        BlockchainCore core = new BlockchainCore(ChainConfig.DIFFICULTY);
        core.getChain().clear();
        core.getChain().add(createCustomGenesisBlock());
        return core;
    }

    public static boolean isGenesisBlock(Block block) {
        return block.getIndex() == 0 && block.getPreviousHash().equals(GENESIS_PREVIOUS_HASH);
    }
}

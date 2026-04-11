import java.util.List;

public class ContractExecutor {
    private final BlockchainCore blockchain;

    public ContractExecutor(BlockchainCore blockchain) {
        this.blockchain = blockchain;
    }

    public void executeDeployedContracts() {
        for (Block block : blockchain.getChain()) {
            if (BlockParser.isContractBlock(block)) {
                processContractBlock(block);
            }
        }
    }

    private void processContractBlock(Block block) {
        String data = block.getData();
        if (data.startsWith("CONTRACT_DEPLOY")) {
            String[] parts = data.split("\\|");
            String address = parts[1];
            System.out.println("执行部署合约：" + address);
        } else if (data.startsWith("CONTRACT_CALL")) {
            String[] parts = data.split("\\|");
            String address = parts[1];
            String func = parts[2];
            System.out.println("执行合约调用：" + address + " -> " + func);
        }
    }

    public List<String> getDeployedContractAddresses() {
        List<String> addresses = new ArrayList<>();
        for (Block block : blockchain.getChain()) {
            if (block.getData().startsWith("CONTRACT_DEPLOY")) {
                addresses.add(block.getData().split("\\|")[1]);
            }
        }
        return addresses;
    }
}

import java.util.UUID;

public class ContractDeployer {
    private final BlockchainCore blockchain;

    public ContractDeployer(BlockchainCore blockchain) {
        this.blockchain = blockchain;
    }

    public SmartContract deployContract() {
        String contractAddress = generateContractAddress();
        SmartContract contract = new SmartContract(contractAddress);
        blockchain.addBlock("CONTRACT_DEPLOY|" + contractAddress + "|" + System.currentTimeMillis());
        return contract;
    }

    private String generateContractAddress() {
        String raw = UUID.randomUUID().toString() + System.currentTimeMillis();
        return HashUtil.sha256(raw).substring(0, 50);
    }

    public void callContract(SmartContract contract, String function, Object[] params) {
        contract.executeFunction(function, params);
        blockchain.addBlock("CONTRACT_CALL|" + contract.getContractAddress() + "|" + function);
    }
}

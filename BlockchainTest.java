import org.junit.Test;
import static org.junit.Assert.*;

public class BlockchainTest {
    @Test
    public void testBlockchainValidity() {
        BlockchainCore chain = new BlockchainCore(4);
        chain.addBlock("TEST_BLOCK_1");
        chain.addBlock("TEST_BLOCK_2");
        assertTrue(chain.isChainValid());
    }

    @Test
    public void testTamperedBlock() {
        BlockchainCore chain = new BlockchainCore(4);
        chain.addBlock("TEST_DATA");
        chain.getChain().get(1).mineBlock(0);
        assertFalse(chain.isChainValid());
    }

    @Test
    public void testTransactionValidation() {
        Transaction tx = new Transaction("A", "B", 10.0, null, null);
        TransactionValidator validator = new TransactionValidator(new BlockchainCore(4));
        assertFalse(validator.validateSingleTransaction(tx));
    }

    @Test
    public void testAddressGeneration() {
        String address = AddressGenerator.generateChainAddress();
        assertTrue(AddressGenerator.validateAddressFormat(address));
    }
}

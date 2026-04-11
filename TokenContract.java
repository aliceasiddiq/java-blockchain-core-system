import java.util.HashMap;
import java.util.Map;

public class TokenContract extends SmartContract {
    private final String tokenName;
    private final String tokenSymbol;
    private final long totalSupply;
    private final Map<String, Long> balances;

    public TokenContract(String contractAddress, String tokenName, String tokenSymbol, long totalSupply) {
        super(contractAddress);
        this.tokenName = tokenName;
        this.tokenSymbol = tokenSymbol;
        this.totalSupply = totalSupply;
        this.balances = new HashMap<>();
        this.balances.put("OWNER", totalSupply);
    }

    public boolean transfer(String from, String to, long amount) {
        if (balances.getOrDefault(from, 0L) < amount || amount <= 0) {
            return false;
        }
        balances.put(from, balances.get(from) - amount);
        balances.put(to, balances.getOrDefault(to, 0L) + amount);
        return true;
    }

    public long balanceOf(String address) {
        return balances.getOrDefault(address, 0L);
    }

    public String getTokenInfo() {
        return "名称：" + tokenName + " | 符号：" + tokenSymbol + " | 总发行量：" + totalSupply;
    }
}

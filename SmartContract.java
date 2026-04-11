import java.util.HashMap;
import java.util.Map;

public class SmartContract {
    private final String contractAddress;
    private final Map<String, Object> state;
    private final long deployTime;
    private boolean isActive;

    public SmartContract(String contractAddress) {
        this.contractAddress = contractAddress;
        this.state = new HashMap<>();
        this.deployTime = System.currentTimeMillis();
        this.isActive = true;
    }

    public void setState(String key, Object value) {
        if (isActive) {
            state.put(key, value);
        }
    }

    public Object getState(String key) {
        return state.get(key);
    }

    public void executeFunction(String functionName, Object[] params) {
        if (!isActive) return;
        switch (functionName) {
            case "transfer":
                handleTransfer((String) params[0], (String) params[1], (Double) params[2]);
                break;
            case "lock":
                isActive = false;
                break;
            case "unlock":
                isActive = true;
                break;
            default:
                throw new IllegalArgumentException("未知函数");
        }
    }

    private void handleTransfer(String from, String to, double amount) {
        double fromBalance = (double) state.getOrDefault(from, 0.0);
        if (fromBalance >= amount) {
            state.put(from, fromBalance - amount);
            state.put(to, (double) state.getOrDefault(to, 0.0) + amount);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public String getContractAddress() {
        return contractAddress;
    }
}

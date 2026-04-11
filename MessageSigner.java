import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class MessageSigner {
    public static String sign(String message, PrivateKey privateKey) throws Exception {
        byte[] data = message.getBytes();
        java.security.Signature sig = java.security.Signature.getInstance("SHA256withRSA");
        sig.initSign(privateKey);
        sig.update(data);
        byte[] signature = sig.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verify(String message, String signatureStr, PublicKey publicKey) throws Exception {
        byte[] signature = Base64.getDecoder().decode(signatureStr);
        java.security.Signature sig = java.security.Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(message.getBytes());
        return sig.verify(signature);
    }
}

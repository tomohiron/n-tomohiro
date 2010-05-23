package sandbox.crypto;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class CryptoSample03 {

    public static void main(String[] args) throws Exception {
        byte[] pssKey;

        // パスワード
        String password = "pswd";

        // パスワードからキーを作成
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        pssKey = md.digest();

        // 準備
        DESKeySpec desKeySpec = new DESKeySpec(pssKey);
        SecretKeyFactory desKeyFac = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = desKeyFac.generateSecret(desKeySpec);

        // 暗号化
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.ENCRYPT_MODE, desKey);
        byte input[] = "This ia an original message.".getBytes();
        byte encrypted[] = c.doFinal(input); // 暗号化データ

        // 復号処理

        c.init(Cipher.DECRYPT_MODE, desKey);
        byte output[] = c.doFinal(encrypted); // 復号したデータ

        // 表示
        System.out.println("The string was ");
        System.out.println(new String(output));
    }
}

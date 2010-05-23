package sandbox.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class CryptoSample02 {

    public static void main(String[] args) throws Exception {
        // 鍵
        String kagi = "abc12345";
        DESKeySpec dk = new DESKeySpec(kagi.getBytes());
        SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
        SecretKey sk = kf.generateSecret(dk);

        // 暗号化
        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.ENCRYPT_MODE, sk);
        byte input[] = "This ia an original message.".getBytes();
        byte encrypted[] = c.doFinal(input); // 暗号化データ

        // 復号処理
        c.init(Cipher.DECRYPT_MODE, sk);
        byte output[] = c.doFinal(encrypted); // 復号したデータ

        // 表示
        System.out.println("The string was ");
        System.out.println(new String(output));
    }
}

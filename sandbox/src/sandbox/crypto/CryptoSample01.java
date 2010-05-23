package sandbox.crypto;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

public class CryptoSample01 {

    public static void main(String[] args) throws Exception {
        // 暗号化
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        Key key = kg.generateKey();
        Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte input[] = "To be encrypted data.".getBytes();
        byte encrypted[] = c.doFinal(input); // ここで暗号化

        // ここから復号処理
        byte iv[] = c.getIV();

        IvParameterSpec dps = new IvParameterSpec(iv);
        c.init(Cipher.DECRYPT_MODE, key, dps);
        byte output[] = c.doFinal(encrypted);

        // 表示
        System.out.print("The string was \"");
        System.out.println(new String(output) + "\"");
    }
}

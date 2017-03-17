package error.licen.com.erroredittext.method;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesEncryptMethod {

    private static final String KEY = "YOUR KEY";
    private static final String IV = "YOUR IV";

    public static String encryptString(String inputString) {
        byte[] TextByte = null;
        try {
            SecretKeySpec mSecretKeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
            AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(IV.getBytes("UTF-8"));
            Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            mCipher.init(Cipher.ENCRYPT_MODE, mSecretKeySpec, mAlgorithmParameterSpec);
            TextByte = mCipher.doFinal(inputString.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return Base64.encodeToString(TextByte, Base64.DEFAULT);
    }

    public static String decryptString(String inputString) {
        byte[] TextByte = null;
        String decryptString = null;
        try {
            SecretKeySpec mSecretKeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
            AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(IV.getBytes("UTF-8"));
            Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            mCipher.init(Cipher.DECRYPT_MODE, mSecretKeySpec, mAlgorithmParameterSpec);
            TextByte = mCipher.doFinal(Base64.decode(inputString.getBytes("UTF-8"), Base64.DEFAULT));
            decryptString = new String(TextByte,"UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return decryptString;
    }
}

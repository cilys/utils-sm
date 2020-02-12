package com.cilys.utils.sm;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class Sm4Utils extends BaseSmUtils{

    private final static String ALGORITHM_NAME = "SM4";

    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    public final static int DEFAULT_KEY_SIZE = 128;

    private static Cipher gennerateEcbCipher(String algorithmName, int mode, byte[] key) throws Exception, NoSuchAlgorithmException, NoSuchProviderException {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    public static byte[] generateKey() throws Exception {
        return generateKey(DEFAULT_KEY_SIZE);
    }

    public static byte[] generateKey(int keySize) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(keySize, new SecureRandom());
        return kg.generateKey().getEncoded();
    }


    public static String encryEcb(String hexKey, String paramStr) throws Exception {
        String cipherText = "";
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        byte[] srcData = paramStr.getBytes(ENCODING);
        byte[] cipherArray = encrypt_Ecb_Padding(keyData, srcData);
        cipherText = ByteUtils.toHexString(cipherArray);
        out("Sm4Utils加密原文："  + paramStr + "\t密钥：" + hexKey + "\t加密后：" + cipherText);
        return cipherText;
    }

    public static byte[] encrypt_Ecb_Padding(byte[] key, byte[] data) throws Exception{
        Cipher cipher = gennerateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static String decryptEcb(String hexKey, String cipherText) throws Exception{
        byte[] keyData = ByteUtils.fromHexString(hexKey);
        byte[] cipherData = ByteUtils.fromHexString(cipherText);

        byte[] srcData = decrypt_Ecb_Padding(keyData, cipherData);
        String result = new String(srcData, ENCODING);
        out("Sm4Utils解密秘文：" + cipherText + "\t密钥：" + hexKey + "\t解密：" + result);
        return result;
    }

    public static byte[] decrypt_Ecb_Padding(byte[] keyData, byte[] cipherData) throws Exception{
        Cipher cipher = gennerateEcbCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, keyData);
        return cipher.doFinal(cipherData);
    }


}

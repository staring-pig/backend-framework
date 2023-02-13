package com.staringpig.framework.wechat.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

/**
 * AES解密类。
 */
public final class AES {
    private static boolean isInitialized = false;

    /**
     * AES解密
     *
     * @param content 密文
     * @param keyByte 秘钥
     * @param ivByte  偏移
     */
    public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws Exception {
        return decrypt(content, keyByte, ivByte, "AES/CBC/PKCS7Padding");
    }

    /**
     * AES解密
     *
     * @param dataBytes 密文
     * @param keyBytes  秘钥
     * @param ivBytes   偏移
     * @param algorithm 加密算法。
     * @return
     */
    public static byte[] decrypt(byte[] dataBytes, byte[] keyBytes, byte[] ivBytes, String algorithm) throws Exception {
        if (!isInitialized) {
            Security.addProvider(new BouncyCastleProvider());
            isInitialized = true;
        }
        Cipher cipher = Cipher.getInstance(algorithm);
        Key sKeySpec = new SecretKeySpec(keyBytes, "AES");

        AlgorithmParameters ivParams = AlgorithmParameters.getInstance("AES");
        ivParams.init(new IvParameterSpec(ivBytes));

        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, ivParams);
        return cipher.doFinal(dataBytes);
    }
}

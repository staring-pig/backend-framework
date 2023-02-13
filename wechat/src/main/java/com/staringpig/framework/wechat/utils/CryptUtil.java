package com.staringpig.framework.wechat.utils;

import net.dreamlu.mica.core.utils.$;

/**
 * 安全工具。
 *
 * <p>
 * 该类实现一些加密、解密相关的功能。
 */
//@Log4j2
public final class CryptUtil {
    /**
     * 对指定的密文进行AES解密。
     *
     * @param data 密文（base64）。
     * @param key  秘钥（base64）。
     * @param iv   偏移向量（base64）。
     * @return
     */
    public static String decryptAES(String data, String key, String iv) {
        return decryptAES(data, key, iv, "UTF-8");
    }

    /**
     * 对指定的密文进行AES解密。
     *
     * @param data    密文（base64）。
     * @param key     秘钥（base64）。
     * @param iv      偏移向量（base64）。
     * @param charset 字符集。
     * @return
     */
    public static String decryptAES(String data, String key, String iv, String charset) {
        if ($.isEmpty(data)) {
            return "";
        }
        try {
            byte[] encryptedBytes = Base64Util.decode(data);
            byte[] keyBytes = Base64Util.decode(key);
            byte[] ivBytes = Base64Util.decode(iv);

            byte[] bytes = AES.decrypt(encryptedBytes, keyBytes, ivBytes);

            return new String(bytes, charset);
        } catch (Exception ex) {
//            log.error("fail to decrypt data. key={}, iv={}, exception={}", key, iv, ex);
            return null;
        }
    }

}

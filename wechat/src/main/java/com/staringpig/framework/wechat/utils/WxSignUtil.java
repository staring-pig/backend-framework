package com.staringpig.framework.wechat.utils;

import com.staringpig.framework.wechat.client.WechatServerException;
import com.staringpig.framework.wechat.client.api.miniprogram.PhoneInfoQuery;
import net.dreamlu.mica.core.utils.$;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author:Tebon time:2021-7-21 14:07
 */
public class WxSignUtil {
    private static final String WECHAT_OPEN_URL = "https://open.weixin.qq.com";

    public static void main(String[] args) {
        checkSignature("123", "123", "123", "123");
    }

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        String[] arr = new String[]{token, timestamp, nonce};
        // 将 token, timestamp, nonce 三个参数进行字典排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String s : arr) {
            content.append(s);
        }
        MessageDigest md;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行 shal 加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null && tmpStr.equals(signature.toUpperCase());
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param digest
     * @return
     */
    private static String byteToStr(byte[] digest) {
        // TODO Auto-generated method stub
        StringBuilder strDigest = new StringBuilder();
        for (byte b : digest) {
            strDigest.append(byteToHexStr(b));
        }
        return strDigest.toString();
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param b
     * @return
     */
    private static String byteToHexStr(byte b) {
        // TODO Auto-generated method stub
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(b >>> 4) & 0X0F];
        tempArr[1] = Digit[b & 0X0F];

        return new String(tempArr);
    }

    public static String oauth2buildAuthorizationUrl(String appId, String redirectURI, String scope, String state) {
        return String.format(WECHAT_OPEN_URL + "/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&connect_redirect=1#wechat_redirect",
                appId, $.encodeBase64UrlSafe(redirectURI), scope, $.toStr(state, ""));
    }

    public static PhoneInfoQuery.Result decryptAESPhoneInfo(PhoneInfoQuery phoneInfoQuery) throws WechatServerException {
        String decrypt = CryptUtil.decryptAES(phoneInfoQuery.getEncryptedData(), phoneInfoQuery.getSessionKey(), phoneInfoQuery.getIv());
        if (decrypt != null) {
            return $.readJson(decrypt, PhoneInfoQuery.Result.class);
        }
        throw new WechatServerException("decrypt fail param" + phoneInfoQuery);
    }
}

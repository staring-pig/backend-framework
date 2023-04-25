package com.staringpig.framework.wechat.offiaccount.message.listener;

import com.staringpig.framework.wechat.offiaccount.OffiAccount;
import com.staringpig.framework.wechat.offiaccount.message.dispatcher.OAMessageDispatcher;
import com.staringpig.framework.wechat.offiaccount.message.listener.api.PublishedMessageData;
import com.staringpig.framework.wechat.offiaccount.message.listener.api.ReceivedMessageData;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;

/**
 * 基本的消息监听
 */
public abstract class BaseMessageListener implements MessageListener {

    protected final OffiAccount offiAccount;
    protected final OAMessageDispatcher oaMessageDispatcher;
    protected final OAMessageConvertor oaMessageConvertor;

    protected BaseMessageListener(OffiAccount offiAccount,
                                  OAMessageDispatcher oaMessageDispatcher, OAMessageConvertor oaMessageConvertor) {
        this.offiAccount = offiAccount;
        this.oaMessageDispatcher = oaMessageDispatcher;
        this.oaMessageConvertor = oaMessageConvertor;
    }

    @Override
    public Optional<PublishedMessageData> on(ReceivedMessageData message) {
        return oaMessageDispatcher.dispatch(oaMessageConvertor.convert(message)).map(oaMessageConvertor::convert);
    }

    @Override
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        return checkSignature(signature, timestamp, nonce, offiAccount.getToken());
    }

    /**
     * 验证签名
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
     */
    private static String byteToHexStr(byte b) {
        // TODO Auto-generated method stub
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(b >>> 4) & 0X0F];
        tempArr[1] = Digit[b & 0X0F];

        return new String(tempArr);
    }
}

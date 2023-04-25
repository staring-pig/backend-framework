package com.staringpig.framework.wechat.offiaccount.web;

import com.staringpig.framework.wechat.Endpoint;
import com.staringpig.framework.wechat.accesstoken.AccessTokenEndpoint;
import com.staringpig.framework.wechat.ServerException;
import com.staringpig.framework.wechat.offiaccount.OffiAccountAPI;
import com.staringpig.framework.wechat.offiaccount.api.OAWebPageAccessCommand;
import com.staringpig.framework.wechat.offiaccount.api.OAWebPageJsTicketCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Endpoint
public class OAWebEndpoint {

    private final OffiAccountAPI api;
    private final AccessTokenEndpoint accessTokenEndpoint;

    public OAWebEndpoint(OffiAccountAPI api, AccessTokenEndpoint accessTokenEndpoint) {
        this.api = api;
        this.accessTokenEndpoint = accessTokenEndpoint;
    }

    private static final String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public OAWebPageAccessCommand.Result webAccessToken(String appId, String appSecret, String code) {
        OAWebPageAccessCommand.Result result;
        try {
            result = api.oaWebAccessToken(appId, appSecret, code).execute().body();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
        Objects.requireNonNull(result).isOK();
        return result;
    }

    public JsSDKSign jsSDKSign(String appId, String appSecret, String url) {
        String accessToken = accessTokenEndpoint.accessToken(appId, appSecret);
        OAWebPageJsTicketCommand.Result ticket;
        try {
            ticket = api.oaWebTicket(accessToken).execute().body();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
        String nonce = createNonceStr();
        String timestamp = String.format("%010d", System.currentTimeMillis() / 1000);
        String plain = "jsapi_ticket=" + Objects.requireNonNull(ticket).getTicket()
                + "&noncestr=" + nonce + "&timestamp=" + timestamp + "&url=" + url;
        return JsSDKSign.builder().nonceStr(nonce).signature(SHA1(plain)).timestamp(timestamp).build();
    }

    private static String createNonceStr() {
        StringBuilder nonceStr = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int beginIndex = (int) Math.round(Math.random() * 10);
            nonceStr.append(str.charAt(beginIndex));
        }
        return nonceStr.toString();
    }

    private static String SHA1(String content) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(content.getBytes());
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (byte b : messageDigest) {
                String shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class JsSDKSign {
        /**
         * 必填，生成签名的时间戳
         */
        private String timestamp;
        /**
         * 必填，生成签名的随机串
         */
        private String nonceStr;
        /**
         * 必填，签名
         */
        private String signature;
    }
}

package com.staringpig.framework.wechat;

import com.staringpig.framework.wechat.client.OffiAccountClient;
import com.staringpig.framework.wechat.client.WechatAccessTokenClient;
import com.staringpig.framework.wechat.client.api.corp.AccessTokenQuery;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageAccessCommand;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageJsTicketCommand;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageUserInfoQuery;
import com.staringpig.framework.wechat.offiaccount.OffiAccountApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ldh
 * time 2022-4-14 16:10
 */
public class RemoteOAWebPageService implements OAWebPageService {

    private static final String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final WechatAccessTokenClient wechatAccessTokenClient;
    private final OffiAccountApplication offiAccountApplication;
    private final OffiAccountClient offiAccountClient;

    public RemoteOAWebPageService(WechatAccessTokenClient wechatAccessTokenClient,
                                  OffiAccountApplication offiAccountApplication,
                                  OffiAccountClient offiAccountClient) {
        this.wechatAccessTokenClient = wechatAccessTokenClient;
        this.offiAccountApplication = offiAccountApplication;
        this.offiAccountClient = offiAccountClient;
    }

    @Override
    public OAWebPageAccessCommand.Result webPageAccess(String code) {
        OAWebPageAccessCommand.Result result = offiAccountClient.oaWebPageAccessToken(
                offiAccountApplication.getAppId(), offiAccountApplication.getAppSecret(), code);
        result.isOK();
        return result;
    }

    @Override
    public String fetchOpenId(String code) {
        return this.webPageAccess(code).getOpenid();
    }

    @Override
    public OAWebPageUserInfoQuery.Result gzhWebPageUserInfo(String webAccessToken, String openId) {
        OAWebPageUserInfoQuery.Result result = offiAccountClient.gzhWebPageUserInfo(webAccessToken, openId);
        result.isOK();
        return result;
    }

    @Override
    public JsSDKSign jsSDKSign(String url) {
        AccessTokenQuery.Result accessToken =
                wechatAccessTokenClient.getAccessToken(offiAccountApplication.getAppId(),
                        offiAccountApplication.getAppSecret());
        accessToken.isOK();
        OAWebPageJsTicketCommand.Result ticket = offiAccountClient.oaWebPageTicket(accessToken.getAccessToken());
        String nonce = createNonceStr();
        String timestamp = String.format("%010d", System.currentTimeMillis() / 1000);
        String plain = "jsapi_ticket=" + ticket.getTicket() + "&noncestr=" + nonce + "&timestamp=" + timestamp + "&url=" + url;
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
}

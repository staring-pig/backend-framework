package wechat;//package com.tebon.tot.wechat;
//
//import com.tebon.WechatApplication;
//import com.tebon.tots.wechat.client.MiniAppAuthClient;
//import com.tebon.tots.wechat.client.MiniAppMessageClient;
//import com.tebon.tots.wechat.client.MiniAppQrCodeClient;
//import com.tebon.tots.wechat.client.MiniAppShopApiService;
//import com.tebon.tots.wechat.client.dto.req.CreateWxaQrCodeReqDTO;
//import com.tebon.tots.wechat.client.dto.req.ShopCouponListReqDTO;
//import com.tebon.tots.wechat.client.dto.req.ShopProductListReqDTO;
//import com.tebon.tots.wechat.client.dto.req.ShopProductSpuReqDTO;
//import com.tebon.tots.wechat.client.dto.req.SubscribeMessageReqDTO;
//import com.tebon.tots.wechat.client.dto.resp.FileRespDTO;
//import com.tebon.tots.wechat.client.dto.resp.GetAccessTokenRespDTO;
//import com.tebon.tots.wechat.client.dto.resp.GetCouponListRespDTO;
//import com.tebon.tots.wechat.client.dto.resp.GetProductListRespDTO;
//import com.tebon.tots.wechat.client.dto.resp.GetSpuInfoRespDTO;
//import com.tebon.tots.wechat.client.dto.resp.SubscribeMessageRespDTO;
//import com.tebon.tots.wechat.client.dto.resp.XcxNotifyRespDTO;
//import com.tebon.tots.wechat.props.MiniAppProperties;
//import com.tebon.tots.wechat.utils.CryptUtil;
//import feign.Response;
//import net.dreamlu.mica.core.utils.$;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@SpringBootTest(classes = WechatApplication.class)
//@RunWith(SpringRunner.class)
//public class MiniAppTest {
//    @Autowired
//    MiniAppAuthClient miniAppAuthClient;
//    @Autowired
//    MiniAppMessageClient miniAppMessageClient;
//    @Autowired
//    MiniAppQrCodeClient miniAppQrCodeClient;
//    @Autowired
//    MiniAppShopApiService miniAppShopApiService;
//    @Autowired
//    MiniAppProperties miniAppProperties;
//    private static final String token = "47_bixqbidmGDP_ZMqSYVcgLWhcQ6PVVqbKgnDPof0qpu-njycIBk_0-L8ng4jZXat9SVb3iWyeCPeeuvQTPXYXFynKrpYqEPWpQ0OOpDbbKg36CTHEw80nmIxp__pcyDES8X6HzYyz13WuuOvlRZFiADAIQW";
//
//    @Test
//    public void decryptAESPhoneInfo() {
//        String data = "FNnSiuquCEH2NHQLucI0KIlC6cr1AhHNtydUc9JPaKtYODP1GtFPiR5Vn5s8Wzzj4frnhRKdvHQhb5UJ+E721KiFvXgaqA/0BOwkcu/RmjXpkkcdHvlG+N1usVxFjrS6thwoHDwXryVOsqewILTtPbPviLBK+6ktKZOFyuoexPs+J86kkvnP/U0kt9MwRhclX5oLu7ObgeppC4yfa3W36Q==";
//        String decrypt = CryptUtil.decryptAES(data, "m7doOnwuLNcVdKBZxSdoqw==", "2vvsN0XdB0KFCesLAfTX9g==");
//        System.out.println("decrypt = " + decrypt);
//    }
//
//    @Test
//    public void getAccessToken() {
//        Optional<GetAccessTokenRespDTO> getAccessTokenRespDTO = miniAppAuthClient.getAccessToken(miniAppProperties.getAppId()
//                , miniAppProperties.getAppSecret());
//        getAccessTokenRespDTO.ifPresent(respDTO ->
//                System.out.println(respDTO.getErrCode()
//                        + "------" + respDTO.getErrMsg()
//                        + "------" + respDTO));
//    }
//
//    @Test
//    public void xcxNotify() {
//        //code换取openId和sessionKey,可以给自定义登录态
//        Optional<XcxNotifyRespDTO> xcxNotifyRespDTO = miniAppAuthClient.xcxNotify(miniAppProperties.getAppId()
//                , miniAppProperties.getAppSecret(), "041qmnFa18lFsB0H9cJa1FKaIB3qmnFz");
//        xcxNotifyRespDTO.ifPresent(getAccessTokenRespDTO ->
//                System.out.println(getAccessTokenRespDTO.getErrCode()
//                        + "------" + getAccessTokenRespDTO.getErrMsg()
//                        + "------" + getAccessTokenRespDTO));
//    }
//
//    @Test
//    public void subscribeMessage() {
//        String paramJson = "{\"touser\":\"OPENID\",\"template_id\":\"TEMPLATE_ID\",\"page\":\"index\",\"miniprogram_state\":\"developer\",\"lang\":\"zh_CN\",\"data\":{\"number01\":{\"value\":\"339208499\"},\"date01\":{\"value\":\"2015年01月05日\"},\"site01\":{\"value\":\"TIT创意园\"},\"site02\":{\"value\":\"广州市新港中路397号\"}}}";
//        SubscribeMessageReqDTO subscribeMessageReqDTO = $.readJson(paramJson, SubscribeMessageReqDTO.class);
//        if (subscribeMessageReqDTO != null) {
//            subscribeMessageReqDTO.setTouser("openId");
//        }
//        Optional<SubscribeMessageRespDTO> subscribeMessageRespDTO = miniAppMessageClient.subscribeMessage(token, subscribeMessageReqDTO);
//        subscribeMessageRespDTO.ifPresent(getAccessTokenRespDTO ->
//                System.out.println(getAccessTokenRespDTO.getErrCode()
//                        + "------" + getAccessTokenRespDTO.getErrMsg()
//                        + "------" + getAccessTokenRespDTO));
//    }
//
//    @Test
//    public void createWxaQrCode() throws IOException {
//        CreateWxaQrCodeReqDTO createWxaQrCodeReqDTO = new CreateWxaQrCodeReqDTO();
//        Response response = miniAppQrCodeClient.createWxaQrCode(token, createWxaQrCodeReqDTO);
//        FileRespDTO fileRespDTO = new FileRespDTO();
//        fileRespDTO.getByteByFile(response);
//        System.out.println(fileRespDTO.getErrCode()
//                + "------" + fileRespDTO.getErrMsg()
//                + "------" + fileRespDTO);
//    }
//
//    @Test
//    public void getProductList() {
//        ShopProductListReqDTO shopProductListReqDTO = new ShopProductListReqDTO();
//        shopProductListReqDTO.setStatus(0);
//        shopProductListReqDTO.setNeedEditSpu(1);
//        shopProductListReqDTO.setPage(1);
//        shopProductListReqDTO.setPageSize(10);
//        Optional<GetProductListRespDTO> getProductListRespDTO = miniAppShopApiService.getProductList(token, shopProductListReqDTO);
//        getProductListRespDTO.ifPresent(getAccessTokenRespDTO ->
//                System.out.println(getAccessTokenRespDTO.getErrCode()
//                        + "------" + getAccessTokenRespDTO.getErrMsg()
//                        + "------" + getAccessTokenRespDTO));
//    }
//
//    @Test
//    public void getCouponList() {
//        ShopCouponListReqDTO shopCouponListReqDTO = new ShopCouponListReqDTO();
//        shopCouponListReqDTO.setStatus(1);
//        shopCouponListReqDTO.setPage(1);
//        shopCouponListReqDTO.setPageSize(10);
//        Optional<GetCouponListRespDTO> getProductListRespDTO = miniAppShopApiService.getCouponList(token, shopCouponListReqDTO);
//        getProductListRespDTO.ifPresent(getAccessTokenRespDTO ->
//                System.out.println(getAccessTokenRespDTO.getErrCode()
//                        + "------" + getAccessTokenRespDTO.getErrMsg()
//                        + "------" + getAccessTokenRespDTO));
//    }
//
//    @Test
//    public void getSpuInfo() {
//        ShopProductSpuReqDTO shopProductSpuReqDTO = new ShopProductSpuReqDTO();
//        Optional<GetSpuInfoRespDTO> getProductListRespDTO = miniAppShopApiService.getSpuInfo(token, shopProductSpuReqDTO);
//        getProductListRespDTO.ifPresent(getAccessTokenRespDTO ->
//                System.out.println(getAccessTokenRespDTO.getErrCode()
//                        + "------" + getAccessTokenRespDTO.getErrMsg()
//                        + "------" + getAccessTokenRespDTO));
//    }
//}
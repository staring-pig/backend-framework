package wechat;//package com.tebon.tot.wechat;
//
//import com.tebon.WechatApplication;
//import com.tebon.tots.wechat.client.WechatAccountClient;
//import com.tebon.tots.wechat.client.WechatAuthClient;
//import com.tebon.tots.wechat.client.WechatKefuClient;
//import com.tebon.tots.wechat.client.WechatMaterialClient;
//import com.tebon.tots.wechat.client.WechatUserClient;
//import com.tebon.tots.wechat.client.dto.req.CreateQRCodeReqDTO;
//import com.tebon.tots.wechat.client.dto.req.CustomMessageReqDTO;
//import com.tebon.tots.wechat.client.dto.req.SendTemplateMessageReqDTO;
//import com.tebon.tots.wechat.client.dto.resp.CreateQRCodeRespDTO;
//import com.tebon.tots.wechat.client.dto.resp.FileRespDTO;
//import com.tebon.tots.wechat.client.dto.resp.GetAccessTokenRespDTO;
//import com.tebon.tots.wechat.client.dto.resp.GetUserInfoRespDTO;
//import com.tebon.tots.wechat.client.dto.resp.SendMessageRespDTO;
//import com.tebon.tots.wechat.client.dto.resp.SendTemplateMessageRespDTO;
//import com.tebon.tots.wechat.props.WechatProperties;
//import feign.Response;
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
//public class WechatTest {
//    @Autowired
//    WechatKefuClient wechatKefuClient;
//    @Autowired
//    WechatAccountClient wechatAccountClient;
//    @Autowired
//    WechatAuthClient wechatAuthClient;
//    @Autowired
//    WechatUserClient wechatUserClient;
//    @Autowired
//    WechatMaterialClient wechatMaterialClient;
//    @Autowired
//    WechatProperties wechatProperties;
//    private static final String accessToken = "47_K27nu5ltNSOEN0QNxtY5el7tXPdTy_cxBy43JMYLXUvT5FQTxwerNFniUJHhEY5YcoAKteqhYG9q2hRSd1D-Kv1lPIX5pwgOUAHy7acBaue0i9HPCHllUrHiBZGKH4wKA2M0hCQmSysKT62dLFPeAGAUOY";
//
//    @Test
//    public void getAccessToken() {
//        Optional<GetAccessTokenRespDTO> accessTokenRespDTO = wechatAuthClient.getAccessToken(
//                wechatProperties.getAppId(), wechatProperties.getAppSecret());
//        accessTokenRespDTO.ifPresent(respDTO ->
//                System.out.println(respDTO.getErrCode()
//                        + "------" + respDTO.getErrMsg()
//                        + "------" + respDTO.getAccessToken()));
//    }
//
//    @Test
//    public void getUserInfo() {
//        Optional<GetUserInfoRespDTO> userInfo = wechatUserClient.getUserInfo(accessToken, "oLAeX5k8V_tbKfleM8nXHupIiSKo", "zh_CN");
//        userInfo.ifPresent(respDTO ->
//                System.out.println(respDTO.getErrCode()
//                        + "------" + respDTO.getErrMsg()
//                        + "------" + respDTO));
//    }
//
//    @Test
//    public void sendMessage() {
//        CustomMessageReqDTO customMessageReqDTO = new CustomMessageReqDTO();
//        customMessageReqDTO.setTouser("oLAeX5k8V_tbKfleM8nXHupIiSKo");
//        customMessageReqDTO.setMsgtype("text");
//        CustomMessageReqDTO.CustomText customText = new CustomMessageReqDTO.CustomText();
//        customText.setContent("11111");
//        customMessageReqDTO.setText(customText);
//        Optional<SendMessageRespDTO> sendMessageRespDTO = wechatKefuClient.sendMessage(accessToken, customMessageReqDTO);
//        sendMessageRespDTO.ifPresent(respDTO ->
//                System.out.println(respDTO.getErrCode()
//                        + "------" + respDTO.getErrMsg()
//                        + "------" + respDTO));
//    }
//
//    @Test
//    public void sendTemplateMessage() {
//        SendTemplateMessageReqDTO sendTemplateMessageReqDTO = new SendTemplateMessageReqDTO();
//        sendTemplateMessageReqDTO.setTouser("oLAeX5k8V_tbKfleM8nXHupIiSKo");
//        sendTemplateMessageReqDTO.setTemplateId("eqXjHHuK6vlknvWjcpVUtEKKAb8ZVav-BZxKoyb5tR0");
//        Optional<SendTemplateMessageRespDTO> sendTemplateMessageRespDTO = wechatKefuClient.sendTemplateMessage(accessToken, sendTemplateMessageReqDTO);
//        sendTemplateMessageRespDTO.ifPresent(respDTO ->
//                System.out.println(respDTO.getErrCode()
//                        + "------" + respDTO.getErrMsg()
//                        + "------" + respDTO));
//    }
//
//    @Test
//    public void getMedia() throws IOException {
//        Response media = wechatMaterialClient.getMedia(accessToken, "74kJ8cMK2v0wSMS6dGURkGO78J4_Pc51dZVqa4UukjbgM9rTO2tjvW0QyW6mxMXW");
//        FileRespDTO fileRespDTO = new FileRespDTO();
//        fileRespDTO.getByteByFile(media);
//        System.out.println(fileRespDTO.getErrCode()
//                + "------" + fileRespDTO.getErrMsg()
//                + "------" + fileRespDTO);
//    }
//
//
//    @Test
//    public void createQRCode() {
//        CreateQRCodeReqDTO createQRCodeReqDTO = new CreateQRCodeReqDTO();
//        createQRCodeReqDTO.setActionName("QR_STR_SCENE");
//        createQRCodeReqDTO.setExpireSeconds(3001);
//        CreateQRCodeReqDTO.ActionInfoDTO actionInfoDTO = new CreateQRCodeReqDTO.ActionInfoDTO();
//        CreateQRCodeReqDTO.ActionInfoDTO.SceneDTO sceneDTO = new CreateQRCodeReqDTO.ActionInfoDTO.SceneDTO();
//        sceneDTO.setSceneStr("?tots=测试");
//        actionInfoDTO.setScene(sceneDTO);
//        createQRCodeReqDTO.setActionInfo(actionInfoDTO);
//        Optional<CreateQRCodeRespDTO> createQRCodeRespDTO = wechatAccountClient.createQRCode(accessToken, createQRCodeReqDTO);
//        createQRCodeRespDTO.ifPresent(respDTO ->
//                System.out.println(respDTO.getErrCode()
//                        + "------" + respDTO.getErrMsg()
//                        + "------" + respDTO.getQrCodeUrl()
//                        + "------" + respDTO));
//    }
//
//}
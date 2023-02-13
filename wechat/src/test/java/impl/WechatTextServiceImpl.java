package impl;//package com.tebon.tots.service.impl;
//
//import com.tebon.tots.wechat.client.WechatAuthClient;
//import com.tebon.tots.wechat.client.WechatKefuClient;
//import com.tebon.tots.wechat.client.dto.req.CustomMessageReqDTO;
//import com.tebon.tots.wechat.client.dto.req.MessageRequest;
//import com.tebon.tots.wechat.client.dto.resp.MessageResponse;
//import com.tebon.tots.wechat.client.dto.resp.SendMessageRespDTO;
//import com.tebon.tots.wechat.props.WechatProperties;
//import com.tebon.tots.wechat.service.IWechatTextService;
//import com.tebon.tots.wechat.utils.MessageUtil;
//import net.dreamlu.mica.core.utils.$;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.Optional;
//
///**
// * @author:Tebon time:2021-7-21 14:21
// */
//@Service
//public class WechatTextServiceImpl implements IWechatTextService {
//    @Autowired
//    WechatKefuClient wechatKefuClient;
//    @Autowired
//    WechatAuthClient wechatAuthClient;
//    @Autowired
//    WechatProperties wechatProperties;
//
//    @Override
//    public MessageResponse resolver(String s, MessageRequest messageRequest) {
//        CustomMessageReqDTO customMessageReqDTO = new CustomMessageReqDTO();
//        customMessageReqDTO.setTouser("oLAeX5k8V_tbKfleM8nXHupIiSKo");
//        customMessageReqDTO.setMsgtype("text");
//        CustomMessageReqDTO.CustomText customText = new CustomMessageReqDTO.CustomText();
//        customText.setContent(messageRequest.getContent());
//        customMessageReqDTO.setText(customText);
//        Optional<SendMessageRespDTO> sendMessageRespDTO = wechatKefuClient.sendMessage(
//                wechatAuthClient.getAccessToken(
//                        wechatProperties.getAppId(), wechatProperties.getAppSecret()).get().getAccessToken(), customMessageReqDTO);
//        return MessageUtil.toTextResponse(messageRequest, $.formatDate(new Date()));
//    }
//}

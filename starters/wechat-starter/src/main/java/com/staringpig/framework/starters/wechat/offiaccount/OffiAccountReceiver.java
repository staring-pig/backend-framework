package com.staringpig.framework.starters.wechat.offiaccount;

import com.staringpig.framework.wechat.offiaccount.OffiAccount;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import com.staringpig.framework.wechat.offiaccount.message.OAMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.dispatcher.NormalHandlerDispatcher;
import com.staringpig.framework.wechat.offiaccount.message.event.KeyClickEvent;
import com.staringpig.framework.wechat.offiaccount.message.listener.BaseMessageListener;
import com.staringpig.framework.wechat.offiaccount.message.listener.MessageListener;
import com.staringpig.framework.wechat.offiaccount.message.listener.NormalOAMessageConvertor;
import com.staringpig.framework.wechat.offiaccount.message.listener.api.PublishedMessageData;
import com.staringpig.framework.wechat.offiaccount.message.listener.api.ReceivedMessageData;
import com.staringpig.framework.wechat.offiaccount.user.OAUserStore;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
public class OffiAccountReceiver extends BaseMessageListener implements MessageListener {

    public OffiAccountReceiver(OffiAccount offiAccount,
                               Collection<OAMessageHandler<? extends OAMessage>> messageHandlers,
                               OAUserStore oaUserStore,
                               KeyClickEvent.KeyConverter<? extends KeyClickEvent.Key> keyConverter) {
        super(offiAccount, new NormalHandlerDispatcher(),
                new NormalOAMessageConvertor(offiAccount, oaUserStore, keyConverter));
        super.oaMessageDispatcher.registerMessageHandler(messageHandlers);
    }

    @Transactional
    @PostMapping(value = "${staring-pig.framework.wechat.offi-account.notify-path}",
            produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody
    Object receive(@RequestBody ReceivedMessageData message) {
        log.info("接收到微信消息：" + message.toString());
        Optional<PublishedMessageData> on = super.on(message);
        on.ifPresent(publishedMessageData -> log.info("回复微信消息：" + publishedMessageData));
        on.ifPresent(publishedMessageData -> publishedMessageData.setFromUserName(message.getToUserName()));
        return on.isPresent() ? on.get() : "success";
    }

    @GetMapping(value = "${staring-pig.framework.wechat.offi-account.notify-path}")
    public void checkSignature(@RequestParam(value = "signature") String signature,
                               @RequestParam(value = "timestamp") String timestamp,
                               @RequestParam(value = "nonce") String nonce,
                               @RequestParam(value = "echostr") String echo,
                               HttpServletResponse response) {
        log.info("接收到微信请求，入参: [signature]: [" + signature + "], [timestamp]: [" + timestamp + "]," +
                " [nonce]: [" + nonce + "], [echostr]: [" + echo + "]");
        if (super.checkSignature(signature, timestamp, nonce)) {
            try {
                PrintWriter out = response.getWriter();
                out.print(echo);
                out.close();
            } catch (IOException e) {
                log.error("故障！", e);
            }
        } else {
            log.info("这里存在非法请求！");
        }
    }
}

package com.staringpig.framework.wechat.offiaccount.listener;

import com.staringpig.framework.wechat.offiaccount.OffiAccountApplication;
import com.staringpig.framework.wechat.offiaccount.dispatcher.OAMessageDispatcher;
import com.staringpig.framework.wechat.offiaccount.listener.api.PublishedMessageData;
import com.staringpig.framework.wechat.offiaccount.listener.api.ReceivedMessageData;
import com.staringpig.framework.wechat.utils.WxSignUtil;

import java.util.Optional;

/**
 * 基本的消息监听
 */
public abstract class BaseMessageListener implements MessageListener {

    protected final OffiAccountApplication offiAccountApplication;
    protected final OAMessageDispatcher oaMessageDispatcher;
    protected final OAMessageConvertor oaMessageConvertor;

    protected BaseMessageListener(OffiAccountApplication offiAccountApplication,
                                  OAMessageDispatcher oaMessageDispatcher, OAMessageConvertor oaMessageConvertor) {
        this.offiAccountApplication = offiAccountApplication;
        this.oaMessageDispatcher = oaMessageDispatcher;
        this.oaMessageConvertor = oaMessageConvertor;
    }

    @Override
    public Optional<PublishedMessageData> on(ReceivedMessageData message) {
        return oaMessageDispatcher.dispatch(oaMessageConvertor.convert(message)).map(oaMessageConvertor::convert);
    }

    @Override
    public boolean checkSignature(String signature, String timestamp, String nonce) {
        return WxSignUtil.checkSignature(signature, timestamp, nonce, offiAccountApplication.getToken());
    }
}

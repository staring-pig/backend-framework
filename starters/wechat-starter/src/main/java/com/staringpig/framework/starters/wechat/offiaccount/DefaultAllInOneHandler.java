package com.staringpig.framework.starters.wechat.offiaccount;

import com.staringpig.framework.wechat.offiaccount.message.handler.AllInOneHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.DefaultReply;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.VoiceMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.user.OAUser;

import java.math.BigDecimal;
import java.util.Optional;

public class DefaultAllInOneHandler implements AllInOneHandler {

    protected final DefaultReply defaultReply;

    public DefaultAllInOneHandler(DefaultReply defaultReply) {
        this.defaultReply = defaultReply;
    }

    @Override
    public Optional<ReplyMessage> onScanSubscribed(OAUser oaUser, String eventKey, String ticket) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> onScan(OAUser oaUser, String eventKey, String ticket) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> onSubscribed(OAUser oaUser) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> onUnSubscribed(OAUser oaUser) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> onUpLocation(OAUser oaUser, BigDecimal latitude, BigDecimal longitude,
                                               BigDecimal precision) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> onView(OAUser oaUser, String url) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> receiveImage(OAUser oaUser, String picUrl, String mediaId) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> receiveLink(OAUser oaUser, String title, String description, String url) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> receiveLocation(OAUser oaUser, BigDecimal locationX, BigDecimal locationY,
                                                  BigDecimal scale, String label) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> receiveShortVideo(OAUser oaUser, String mediaId, String thumbMediaId) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> receiveText(OAUser oaUser, String content) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> receiveVideo(OAUser oaUser, String mediaId, String thumbMediaId) {
        return Optional.of(defaultReply.generate(oaUser));
    }

    @Override
    public Optional<ReplyMessage> receiveVoice(OAUser oaUser, String mediaId, VoiceMessage.Format format,
                                               String recognition) {
        return Optional.of(defaultReply.generate(oaUser));
    }
}

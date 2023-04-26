package com.staringpig.framework.wechat.offiaccount.message.listener;

import com.staringpig.framework.support.AllInOne;
import com.staringpig.framework.wechat.offiaccount.OffiAccount;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import com.staringpig.framework.wechat.offiaccount.message.event.KeyClickEvent;
import com.staringpig.framework.wechat.offiaccount.message.event.OAEventMessage;
import com.staringpig.framework.wechat.offiaccount.message.event.ScanEvent;
import com.staringpig.framework.wechat.offiaccount.message.event.ScanSubscribeEvent;
import com.staringpig.framework.wechat.offiaccount.message.event.SubscribeEvent;
import com.staringpig.framework.wechat.offiaccount.message.event.UnsubscribeEvent;
import com.staringpig.framework.wechat.offiaccount.message.event.UpLocationEvent;
import com.staringpig.framework.wechat.offiaccount.message.event.ViewEvent;
import com.staringpig.framework.wechat.offiaccount.message.listener.api.PublishedMessageData;
import com.staringpig.framework.wechat.offiaccount.message.listener.api.ReceivedMessageData;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.ImageMessage;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.LinkMessage;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.ShortVideoMessage;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.TextMessage;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.VideoMessage;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.VoiceMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.ImageReplyMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.MusicReplyMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.NewsReplyMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.TextReplyMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.VideoReplyMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.VoiceReplyMessage;
import com.staringpig.framework.wechat.offiaccount.user.OAUserRepository;
import com.staringpig.framework.wechat.offiaccount.user.OAUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NormalOAMessageConvertor implements OAMessageConvertor {

    private final OffiAccount offiAccount;
    private final OAUserRepository oaUserRepository;
    private final KeyClickEvent.KeyConverter<? extends KeyClickEvent.Key> keyConverter;

    public NormalOAMessageConvertor(OffiAccount offiAccount,
                                    OAUserRepository oaUserRepository,
                                    KeyClickEvent.KeyConverter<? extends KeyClickEvent.Key> keyConverter) {
        this.offiAccount = offiAccount;
        this.oaUserRepository = oaUserRepository;
        this.keyConverter = keyConverter;
    }

    @Override
    public OAMessage convert(ReceivedMessageData messageData) {

        OAMessage.Type type;

        try {
            type = OAMessage.Type.valueOf(messageData.getMsgType());
        } catch (Exception exception) {
            return null;
        }

        OAMessage oaMessage = null;

        OAUser account;
        Optional<OAUser> opAppAccount = oaUserRepository.queryByOpenId(messageData.getFromUserName());
        if (opAppAccount.isEmpty()) {
            OffiAccount.UserInfo userInfo = offiAccount.userInfo(messageData.getFromUserName());
            account = oaUserRepository.saveIt(userInfo.convert(offiAccount.getAppId()));
        } else {
            account = opAppAccount.get();
        }

        switch (type) {
            case event -> {
                OAEventMessage.Type eventType;
                try {
                    eventType = OAEventMessage.Type.valueOf(messageData.getEvent());
                } catch (Exception exception) {
                    return null;
                }
                switch (eventType) {
                    case subscribe -> {
                        if (AllInOne.isNotEmpty(messageData.getEventKey()) ||
                                AllInOne.isNotEmpty(messageData.getTicket())) {
                            oaMessage = new ScanSubscribeEvent(account, messageData.getCreateTime(),
                                    messageData.getEventKey(), messageData.getTicket());
                        } else {
                            oaMessage = new SubscribeEvent(account, messageData.getCreateTime());
                        }
                    }
                    case unsubscribe -> oaMessage = new UnsubscribeEvent(account, messageData.getCreateTime());
                    case SCAN ->
                            oaMessage = new ScanEvent(account, messageData.getCreateTime(), messageData.getEventKey(),
                                    messageData.getTicket());
                    case CLICK -> oaMessage = new KeyClickEvent(account, messageData.getCreateTime(),
                            keyConverter.convert(messageData.getEventKey()));
                    case LOCATION -> oaMessage = new UpLocationEvent(account, messageData.getCreateTime(),
                            BigDecimal.valueOf(messageData.getLatitude()),
                            BigDecimal.valueOf(messageData.getLongitude()),
                            BigDecimal.valueOf(messageData.getPrecision()));
                    case VIEW ->
                            oaMessage = new ViewEvent(account, messageData.getCreateTime(), messageData.getEventKey());
                    default -> {
                    }
                }
            }
            case text -> oaMessage = new TextMessage(String.valueOf(messageData.getMsgId()), account,
                    messageData.getCreateTime(), messageData.getContent());
            case image -> oaMessage = new ImageMessage(String.valueOf(messageData.getMsgId()), account,
                    messageData.getCreateTime(), messageData.getPicUrl(), messageData.getMediaId());
            case voice -> oaMessage = new VoiceMessage(String.valueOf(messageData.getMsgId()), account,
                    messageData.getCreateTime(), messageData.getMediaId(),
                    VoiceMessage.Format.valueOf(messageData.getFormat()), messageData.getRecognition());
            case video -> oaMessage = new VideoMessage(String.valueOf(messageData.getMsgId()), account,
                    messageData.getCreateTime(), messageData.getMediaId(), messageData.getThumbMediaId());
            case shortvideo -> oaMessage = new ShortVideoMessage(String.valueOf(messageData.getMsgId()), account,
                    messageData.getCreateTime(), messageData.getMediaId(), messageData.getThumbMediaId());
            case location -> oaMessage = new LocationMessage(String.valueOf(messageData.getMsgId()), account,
                    messageData.getCreateTime(), BigDecimal.valueOf(messageData.getLocationX()),
                    BigDecimal.valueOf(messageData.getLocationY()), BigDecimal.valueOf(messageData.getScale()),
                    messageData.getLabel());
            case link -> oaMessage = new LinkMessage(String.valueOf(messageData.getMsgId()), account,
                    messageData.getCreateTime(), messageData.getTitle(), messageData.getDescription(),
                    messageData.getUrl());

            // 下面的消息不会被收到
            default -> {
            }
        }

        return oaMessage;
    }

    @Override
    public PublishedMessageData convert(ReplyMessage replyMessage) {

        if (replyMessage == null) {
            return null;
        }

        PublishedMessageData.PublishedMessageDataBuilder messageDataBuilder = PublishedMessageData.builder();
        messageDataBuilder.fromUserName(replyMessage.getOAUser().getOaAppId());
        messageDataBuilder.toUserName(replyMessage.getOAUser().getOpenId());
        messageDataBuilder.createTime(replyMessage.getCreateTime());

        if (replyMessage instanceof ImageReplyMessage imageMessage) {
            messageDataBuilder.image(PublishedMessageData.Image.builder()
                    .mediaId(imageMessage.getMediaId())
                    .build());
        } else if (replyMessage instanceof MusicReplyMessage musicMessage) {
            messageDataBuilder.music(PublishedMessageData.Music.builder()
                    .description(musicMessage.getDescription())
                    .hqMusicUrl(musicMessage.getHqMusicUrl())
                    .musicUrl(musicMessage.getMusicURL())
                    .title(musicMessage.getTitle())
                    .build());
        } else if (replyMessage instanceof NewsReplyMessage newsMessage) {
            List<PublishedMessageData.Article> articles = new ArrayList<>();
            for (NewsReplyMessage.Item article : newsMessage.getArticles()) {
                articles.add(PublishedMessageData.Article.builder()
                        .description(article.getDescription())
                        .picUrl(article.getPicUrl())
                        .title(article.getTitle())
                        .url(article.getUrl())
                        .build());
            }
            messageDataBuilder.articles(articles);
            messageDataBuilder.articleCount(articles.size());
        } else if (replyMessage instanceof TextReplyMessage textMessage) {
            messageDataBuilder.content(textMessage.getContent());
        } else if (replyMessage instanceof VideoReplyMessage videoMessage) {
            messageDataBuilder.video(PublishedMessageData.Video.builder()
                    .description(videoMessage.getDescription())
                    .mediaId(videoMessage.getMediaId())
                    .title(videoMessage.getTitle()).build());
        } else if (replyMessage instanceof VoiceReplyMessage voiceMessage) {
            messageDataBuilder.voice(new PublishedMessageData.Voice(voiceMessage.getMediaId()));
        }

        messageDataBuilder.msgType(replyMessage.getType().name());

        return messageDataBuilder.build();
    }
}

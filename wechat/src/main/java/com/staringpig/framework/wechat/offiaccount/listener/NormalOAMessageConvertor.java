package com.staringpig.framework.wechat.offiaccount.listener;

import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.offiaccount.OAAccountRepository;
import com.staringpig.framework.wechat.offiaccount.OAMessage;
import com.staringpig.framework.wechat.offiaccount.event.KeyClickEvent;
import com.staringpig.framework.wechat.offiaccount.event.OAEventMessage;
import com.staringpig.framework.wechat.offiaccount.event.ScanSubscribeEvent;
import com.staringpig.framework.wechat.offiaccount.event.SubscribeEvent;
import com.staringpig.framework.wechat.offiaccount.event.UnsubscribeEvent;
import com.staringpig.framework.wechat.offiaccount.listener.api.PublishedMessageData;
import com.staringpig.framework.wechat.offiaccount.listener.api.ReceivedMessageData;
import com.staringpig.framework.wechat.offiaccount.ordinary.LinkMessage;
import com.staringpig.framework.wechat.offiaccount.ordinary.TextMessage;
import com.staringpig.framework.wechat.offiaccount.reply.ImageReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.MusicReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.NewsReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.TextReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.VideoReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.VoiceReplyMessage;
import net.dreamlu.mica.core.utils.$;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NormalOAMessageConvertor implements OAMessageConvertor {

    private final OAAccountRepository<? extends OPAppAccount> oaAccountRepository;
    private final KeyClickEvent.KeyConverter<? extends KeyClickEvent.Key> keyConverter;

    public NormalOAMessageConvertor(OAAccountRepository<? extends OPAppAccount> oaAccountRepository,
                                    KeyClickEvent.KeyConverter<? extends KeyClickEvent.Key> keyConverter) {
        this.oaAccountRepository = oaAccountRepository;
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

        OPAppAccount account = null;
        Optional<? extends OPAppAccount> opAppAccount = oaAccountRepository.queryByOpenId(messageData.getFromUserName());
        if (!OAMessage.Type.event.equals(type) ||
                !OAEventMessage.Type.subscribe.equals(OAEventMessage.Type.valueOf(messageData.getEvent()))) {
            if (opAppAccount.isEmpty()) {
                throw new RuntimeException("账号不存在");
            }
            account = opAppAccount.get();
        }

        switch (type) {
            case event:
                OAEventMessage.Type eventType;
                try {
                    eventType = OAEventMessage.Type.valueOf(messageData.getEvent());
                } catch (Exception exception) {
                    return null;
                }
                switch (eventType) {
                    case subscribe:
                        if ($.isNotEmpty(messageData.getEventKey()) || $.isNotEmpty(messageData.getTicket())) {
                            oaMessage = opAppAccount
                                    .map(appAccount -> new ScanSubscribeEvent(appAccount, messageData.getCreateTime(),
                                            messageData.getEventKey(), messageData.getTicket()))
                                    .orElseGet(() -> new ScanSubscribeEvent(messageData.getFromUserName(),
                                            messageData.getCreateTime(), messageData.getEventKey(),
                                            messageData.getTicket()));
                        } else {
                            oaMessage = opAppAccount
                                    .map(appAccount -> new SubscribeEvent(opAppAccount.get(), messageData.getCreateTime()))
                                    .orElseGet(() ->
                                            new SubscribeEvent(messageData.getFromUserName(),
                                                    messageData.getCreateTime()));
                        }
                        break;
                    case unsubscribe:
                        oaMessage = new UnsubscribeEvent(account, messageData.getCreateTime());
                        break;
                    case SCAN:
                    case CLICK:
                        oaMessage = new KeyClickEvent(account, messageData.getCreateTime(),
                                keyConverter.convert(messageData.getEventKey()));
                        break;
                    case LOCATION:
                    case VIEW:
                    default:
                        break;
                }
                break;
            case link:
                oaMessage = new LinkMessage(String.valueOf(messageData.getMsgId()), account,
                        messageData.getCreateTime(), messageData.getTitle(), messageData.getDescription(),
                        messageData.getUrl());
                break;
            case news:
            case text:
                oaMessage = new TextMessage(String.valueOf(messageData.getMsgId()), account,
                        messageData.getCreateTime(), messageData.getContent());
                break;
            case image:
            case music:
            case video:
            case voice:
            case location:
            case shortvideo:
            default:
        }

        return oaMessage;
    }

    @Override
    public PublishedMessageData convert(ReplyMessage replyMessage) {

        if (replyMessage == null) {
            return null;
        }

        PublishedMessageData.PublishedMessageDataBuilder messageDataBuilder = PublishedMessageData.builder();
        messageDataBuilder.fromUserName(replyMessage.getOpAppAccount().getApplication().getAppId());
        messageDataBuilder.toUserName(replyMessage.getOpAppAccount().getOpenId());
        messageDataBuilder.createTime(replyMessage.getCreateTime());

        if (replyMessage instanceof ImageReplyMessage) {
            messageDataBuilder.image(PublishedMessageData.Image.builder()
                    .mediaId(((ImageReplyMessage) replyMessage).getMediaId())
                    .build());
        } else if (replyMessage instanceof MusicReplyMessage) {
            messageDataBuilder.music(PublishedMessageData.Music.builder()
                    .description(((MusicReplyMessage) replyMessage).getDescription())
                    .hqMusicUrl(((MusicReplyMessage) replyMessage).getHqMusicUrl())
                    .musicUrl(((MusicReplyMessage) replyMessage).getMusicURL())
                    .title(((MusicReplyMessage) replyMessage).getTitle())
                    .build());
        } else if (replyMessage instanceof NewsReplyMessage) {
            List<PublishedMessageData.Article> articles = new ArrayList<>();
            for (NewsReplyMessage.Item article : ((NewsReplyMessage) replyMessage).getArticles()) {
                articles.add(PublishedMessageData.Article.builder()
                        .description(article.getDescription())
                        .picUrl(article.getPicUrl())
                        .title(article.getTitle())
                        .url(article.getUrl())
                        .build());
            }
            messageDataBuilder.articles(articles);
            messageDataBuilder.articleCount(articles.size());
        } else if (replyMessage instanceof TextReplyMessage) {
            messageDataBuilder.content(((TextReplyMessage) replyMessage).getContent());
        } else if (replyMessage instanceof VideoReplyMessage) {
            // TODO
        } else if (replyMessage instanceof VoiceReplyMessage) {
            // TODO
        }

        messageDataBuilder.msgType(replyMessage.getType().name());

        return messageDataBuilder.build();
    }
}

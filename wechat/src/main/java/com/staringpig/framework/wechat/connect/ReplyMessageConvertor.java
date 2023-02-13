package com.staringpig.framework.wechat.connect;

import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.connect.event.reply.ArticleReply;
import com.staringpig.framework.wechat.connect.event.reply.EventReply;
import com.staringpig.framework.wechat.connect.event.reply.ImageReply;
import com.staringpig.framework.wechat.connect.event.reply.MusicReply;
import com.staringpig.framework.wechat.connect.event.reply.NewsReply;
import com.staringpig.framework.wechat.connect.event.reply.TextReply;
import com.staringpig.framework.wechat.connect.event.reply.VideoReply;
import com.staringpig.framework.wechat.connect.event.reply.VoiceReply;
import com.staringpig.framework.wechat.offiaccount.reply.ImageReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.MusicReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.NewsReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.TextReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.VideoReplyMessage;
import com.staringpig.framework.wechat.offiaccount.reply.VoiceReplyMessage;
import net.dreamlu.mica.core.utils.$;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReplyMessageConvertor {

    public static Optional<ReplyMessage> convert(OPAppAccount opAppAccount, List<EventReply> eventReplies) {
        if ($.isEmpty(eventReplies)) {
            return Optional.empty();
        }

        if (eventReplies.size() == 1) {
            return convert(opAppAccount, eventReplies.get(0));
        }

        List<NewsReplyMessage.Item> articles = new ArrayList<>();
        for (EventReply eventReply : eventReplies) {
            if (eventReply instanceof ArticleReply) {
                articles.add(new NewsReplyMessage.Item(((ArticleReply) eventReply).getTitle(),
                        ((ArticleReply) eventReply).getUrl(), ((ArticleReply) eventReply).getPicUrl(),
                        ((ArticleReply) eventReply).getUrl()));
            }
            if (eventReply instanceof NewsReply) {
                for (ArticleReply content : ((NewsReply) eventReply).getContents()) {
                    articles.add(new NewsReplyMessage.Item(content.getTitle(), content.getUrl(), content.getPicUrl(),
                            content.getUrl()));
                }
            }
        }

        return Optional.of(new NewsReplyMessage(opAppAccount, Instant.now().getEpochSecond(), articles));
    }

    public static Optional<ReplyMessage> convert(OPAppAccount opAppAccount, EventReply reply) {
        Long time = Instant.now().getEpochSecond();

        if (reply instanceof ArticleReply) {
            return Optional.of(new NewsReplyMessage(opAppAccount, time, List.of(new NewsReplyMessage.Item(
                    ((ArticleReply) reply).getTitle(), ((ArticleReply) reply).getUrl(),
                    ((ArticleReply) reply).getPicUrl(), ((ArticleReply) reply).getUrl()))));
        } else if (reply instanceof ImageReply) {
            return Optional.of(new ImageReplyMessage(opAppAccount, time, ((ImageReply) reply).getMediaId()));
        } else if (reply instanceof MusicReply) {
            return Optional.of(new MusicReplyMessage(opAppAccount, time, ((MusicReply) reply).getThumbMediaId(),
                    ((MusicReply) reply).getMusicURL(), ((MusicReply) reply).getTitle(),
                    ((MusicReply) reply).getHqMusicUrl(), ((MusicReply) reply).getHqMusicUrl()));
        } else if (reply instanceof NewsReply) {
            List<NewsReplyMessage.Item> articles = new ArrayList<>();
            for (ArticleReply content : ((NewsReply) reply).getContents()) {
                articles.add(new NewsReplyMessage.Item(content.getTitle(), content.getUrl(), content.getPicUrl(),
                        content.getUrl()));
            }
            return Optional.of(new NewsReplyMessage(opAppAccount, Instant.now().getEpochSecond(), articles));
        } else if (reply instanceof TextReply) {
            return Optional.of(new TextReplyMessage(opAppAccount, time, ((TextReply) reply).getText()));
        } else if (reply instanceof VideoReply) {
            return Optional.of(new VideoReplyMessage(opAppAccount, time, ((VideoReply) reply).getMediaId(),
                    ((VideoReply) reply).getTitle(), ((VideoReply) reply).getDescription()));
        } else if (reply instanceof VoiceReply) {
            return Optional.of(new VoiceReplyMessage(opAppAccount, time, ((VoiceReply) reply).getMediaId()));
        }
        return Optional.empty();
    }
}

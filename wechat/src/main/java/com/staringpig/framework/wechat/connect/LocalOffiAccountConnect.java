package com.staringpig.framework.wechat.connect;

import com.staringpig.framework.wechat.OPApplication;
import com.staringpig.framework.wechat.account.OPAccount;
import com.staringpig.framework.wechat.connect.event.FollowEvent;
import com.staringpig.framework.wechat.connect.event.SceneFollowEvent;
import com.staringpig.framework.wechat.connect.event.reply.EventReply;
import com.staringpig.framework.wechat.offiaccount.OffiAccountApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Function;

public abstract class LocalOffiAccountConnect implements OffiAccountConnect {

    protected final OffiAccountApplication offiAccountApplication;
    private final Set<Function<FollowEvent, Optional<EventReply>>> followSubscribedMap
            = new CopyOnWriteArraySet<>();
    private final Map<String, Function<SceneFollowEvent, Optional<EventReply>>> sceneFollowSubscribedMap
            = new ConcurrentHashMap<>();

    protected LocalOffiAccountConnect(OffiAccountApplication offiAccountApplication) {
        this.offiAccountApplication = offiAccountApplication;
    }

    @Override
    public void subscribeFollow(Function<FollowEvent, Optional<EventReply>> onEvent) {
        followSubscribedMap.add(onEvent);
    }

    @Override
    public void subscribeSceneFollow(String sceneKey, Function<SceneFollowEvent, Optional<EventReply>> onEvent) {
        sceneFollowSubscribedMap.put(sceneKey, onEvent);
    }

    @Override
    public List<EventReply> notifyFollowEvent(FollowEvent event) {
        List<EventReply> eventReplies = new ArrayList<>();
        for (Function<FollowEvent, Optional<EventReply>> value : this.followSubscribedMap) {
            Optional<EventReply> reply = value.apply(event);
            reply.ifPresent(eventReplies::add);
        }
        return eventReplies;
    }

    @Override
    public Optional<EventReply> notifySceneFollowEvent(SceneFollowEvent event) {
        return sceneFollowSubscribedMap.get(event.getSceneKey()).apply(event);
    }

    @Override
    public <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, T data) {
        this.sendTemplateMessage(messageTemplateId, opAccount, null, null, null, data, null);
    }

    @Override
    public <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, T data, String color) {
        this.sendTemplateMessage(messageTemplateId, opAccount, null, null, null, data, color);
    }

    @Override
    public <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, String url, T data) {
        this.sendTemplateMessage(messageTemplateId, opAccount, url, null, null, data, null);
    }

    @Override
    public <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, String url, T data,
                                        String color) {
        this.sendTemplateMessage(messageTemplateId, opAccount, url, null, null, data, color);
    }

    @Override
    public <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, OPApplication toMiniProgram,
                                        String path, T data) {
        this.sendTemplateMessage(messageTemplateId, opAccount, null, toMiniProgram, path, data, null);
    }

    @Override
    public <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, OPApplication toMiniProgram,
                                        String path, T data, String color) {
        this.sendTemplateMessage(messageTemplateId, opAccount, null, toMiniProgram, path, data, color);
    }

    protected abstract <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, String url,
                                                    OPApplication toMiniProgram, String path, T data, String color);

}

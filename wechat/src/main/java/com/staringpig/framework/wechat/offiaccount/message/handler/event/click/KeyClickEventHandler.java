package com.staringpig.framework.wechat.offiaccount.message.handler.event.click;

import com.staringpig.framework.wechat.offiaccount.message.event.KeyClickEvent;
import com.staringpig.framework.wechat.offiaccount.message.event.OAEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.DefaultReply;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ldh
 * time 2021-8-24 11:10
 */
public class KeyClickEventHandler implements OAEventHandler<KeyClickEvent> {

    private final Map<KeyClickEvent.Key, SpecialClick> specialClickMap;
    private final DefaultReply defaultReply;

    public KeyClickEventHandler(Collection<SpecialClick> specialClicks, DefaultReply defaultReply) {
        this.specialClickMap = specialClicks.stream()
                .collect((Collectors.toMap(SpecialClick::clickKey, Function.identity(), (a, b) -> a)));
        this.defaultReply = defaultReply;
    }

    @Override
    public Class<KeyClickEvent> handleMessageType() {
        return KeyClickEvent.class;
    }

    @Override
    public Optional<ReplyMessage> on(KeyClickEvent event) {
        if (specialClickMap.containsKey(event.getKey())) {
            return specialClickMap.get(event.getKey()).generate(event.getOAUser());
        }
        return Optional.of(defaultReply.generate(event.getOAUser()));
    }


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnumsKey implements KeyClickEvent.Key {

        private String value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EnumsKey enumsKey = (EnumsKey) o;

            return value.equals(enumsKey.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }

    public static class EnumsKeyConverter implements KeyClickEvent.KeyConverter<EnumsKey> {

        private final Map<String, EnumsKey> clickKeyMap = new ConcurrentHashMap<>();

        public EnumsKeyConverter(Collection<SpecialClick> specialClicks) {
            for (SpecialClick specialClick : specialClicks) {
                clickKeyMap.put(specialClick.clickKey().getValue(), specialClick.clickKey());
            }
        }

        @Override
        public EnumsKey convert(String keyString) {
            return this.fetch(keyString);
        }

        public EnumsKey fetch(String keyString) {
            return this.clickKeyMap.get(keyString);
        }

        public boolean contains(KeyClickEvent.Key key) {
            return key instanceof EnumsKey && this.clickKeyMap.containsKey(((EnumsKey) key).getValue());
        }
    }

    public interface SpecialClick {

        /**
         * 当前点击的key
         */
        EnumsKey clickKey();

        /**
         * 生成回复消息
         */
        Optional<ReplyMessage> generate(OAUser user);
    }
}

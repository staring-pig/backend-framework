package com.staringpig.framework.wechat.connect.event;

import com.staringpig.framework.wechat.account.OPAccount;
import lombok.Getter;

public class SceneFollowEvent extends FollowEvent {

    /**
     * 场景
     */
    @Getter
    private final String sceneKey;

    public SceneFollowEvent(OPAccount opAccount, Long timestamp, String sceneKey) {
        super(opAccount, timestamp);
        this.sceneKey = sceneKey;
    }
}

package com.staringpig.framework.wechat.offiaccount.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.WechatClientException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.dreamlu.mica.core.utils.$;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ldh
 * time 2021-11-23 9:30
 */
@ToString
public abstract class BaseButton {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private ButtonType type;
    @Getter
    @Setter
    @JsonProperty(value = "sub_button")
    private List<BaseButton> subButtons = new ArrayList<>();
    /**
     * click必传
     */
    @Setter
    @Getter
    private String key;
    /**
     * view和miniprogram必传
     */
    @Setter
    @Getter
    private String url;
    /**
     * miniprogram必传
     */
    @Setter
    @Getter
    @JsonProperty(value = "appid")
    private String appId;
    /**
     * miniprogram必传
     */
    @Setter
    @Getter
    @JsonProperty(value = "pagepath")
    private String pagePath;
    /**
     * media_id必传
     */
    @Setter
    @Getter
    @JsonProperty(value = "media_id")
    private String mediaId;
    /**
     * 是否是子菜单,默认不是
     */
    private Boolean isSub = false;

    private BaseButton() {
    }

    public void validate() throws WechatClientException {
        if ($.isBlank(this.getName())) {
            throw new WechatClientException("创建微信菜单，参数name必传");
        }
        if (isSub && this.getName().length() > 60) {
            throw new WechatClientException("创建微信菜单，二级菜单标题不超过60个字节");
        }
        if (!isSub && this.getName().length() > 16) {
            throw new WechatClientException("创建微信菜单，一级菜单标题不超过16个字节");
        }
    }

    public BaseButton(ButtonType type) {
        this.type = type;
    }

    public void setSubTrue() {
        isSub = true;
    }

    @Getter
    @ToString
    public enum ButtonType {
        click,
        view,
        miniprogram,
        media_id,
        article_id,
        VIEW_LIMITED,
        view_limited,
        article_view_limited,
        ;
    }
}

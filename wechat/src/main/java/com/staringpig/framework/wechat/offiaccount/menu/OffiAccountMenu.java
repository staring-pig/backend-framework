package com.staringpig.framework.wechat.offiaccount.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.WechatClientException;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ldh
 * time 2021-11-23 9:22
 */
@NoArgsConstructor
@ToString
public class OffiAccountMenu {
    @JsonProperty(value = "button")
    private final List<BaseButton> buttons = new ArrayList<>();

    /**
     * 添加菜单之前先校验参数，validate方法校验失败会直接抛异常
     */
    public void add(BaseButton baseButton) {
        //校验一级菜单数量
        checkButtonSize();
        //校验二级菜单数量
        checkSubButtonSize(baseButton);
        //校验一级菜单参数
        baseButton.validate();
        //校验二级菜单参数
        for (BaseButton subButton : baseButton.getSubButtons()) {
            //声明菜单为二级菜单
            subButton.setSubTrue();
            subButton.validate();
        }
        this.buttons.add(baseButton);

    }

    private void checkSubButtonSize(BaseButton baseButton) {
        if (baseButton.getSubButtons().size() > 5) {
            throw new WechatClientException("创建微信二级菜单，最多创建五个");
        }
    }

    private void checkButtonSize() {
        if (this.buttons.size() == 3) {
            throw new WechatClientException("创建微信一级菜单，最多创建三个");
        }
    }

}

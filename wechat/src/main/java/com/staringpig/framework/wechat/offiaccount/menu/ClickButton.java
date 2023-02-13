package com.staringpig.framework.wechat.offiaccount.menu;

import com.staringpig.framework.wechat.client.WechatClientException;
import net.dreamlu.mica.core.utils.$;

/**
 * @author ldh
 * time 2021-11-23 10:07
 */
public class ClickButton extends BaseButton {

    public ClickButton() {
        super(ButtonType.click);
    }

    @Override
    public void validate() throws WechatClientException {
        super.validate();
        if ($.isBlank(this.getKey())) {
            throw new WechatClientException("创建CLICK类型的菜单，参数key必传");
        }
    }
}

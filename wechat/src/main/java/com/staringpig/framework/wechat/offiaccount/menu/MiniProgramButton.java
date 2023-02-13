package com.staringpig.framework.wechat.offiaccount.menu;

import com.staringpig.framework.wechat.client.WechatClientException;
import net.dreamlu.mica.core.utils.$;

/**
 * @author ldh
 * time 2021-11-23 10:07
 */
public class MiniProgramButton extends BaseButton {

    public MiniProgramButton() {
        super(ButtonType.miniprogram);
    }

    @Override
    public void validate() throws WechatClientException {
        super.validate();
        if ($.isBlank(this.getUrl())) {
            throw new WechatClientException("创建miniprogram类型的菜单，参数url必传");
        }
        if ($.isBlank(this.getAppId())) {
            throw new WechatClientException("创建miniprogram类型的菜单，参数appId必传");
        }
        if ($.isBlank(this.getPagePath())) {
            throw new WechatClientException("创建miniprogram类型的菜单，参数pagePath必传");
        }
    }
}

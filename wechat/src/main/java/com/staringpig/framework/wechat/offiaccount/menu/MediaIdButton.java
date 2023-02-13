package com.staringpig.framework.wechat.offiaccount.menu;

import com.staringpig.framework.wechat.client.WechatClientException;
import net.dreamlu.mica.core.utils.$;

/**
 * @author ldh
 * time 2021-11-23 11:17
 */
public class MediaIdButton extends BaseButton {
    public MediaIdButton() {
        super(ButtonType.media_id);
    }

    @Override
    public void validate() throws WechatClientException {
        super.validate();
        if ($.isBlank(this.getUrl())) {
            throw new WechatClientException("创建media_id类型的菜单，参数url必传");
        }
    }
}

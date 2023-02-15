package com.staringpig.framework.wechat;

import com.staringpig.framework.wechat.client.api.offi.UserInfoQuery;
import com.staringpig.framework.wechat.message.Message;
import com.staringpig.framework.wechat.offiaccount.menu.OffiAccountMenu;

/**
 * 公众号应用服务
 */
public interface OffiAccountService {

    /**
     * 微信小程序登陆，为获取微信数据提供服务
     *
     * @param code 前端js编码
     * @return 加密后的
     */
    UserInfoQuery.Result getUserInfo(String code);

    /**
     * 发送模板消息
     *
     * @param message 消息
     * @param <T>     数据类型
     */
    <T> void sendTemplateMessage(Message<T> message);

    /**
     * 创建菜单
     */
    void createMenu(OffiAccountMenu offiAccountMenu);

    /**
     * 查询菜单
     */
    OffiAccountMenu queryMenu();

    /**
     * 通过客服回复消息
     */
    void sendCustomMessageBySpecialist(String openId, String textContent);
}

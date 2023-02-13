package com.staringpig.framework.wechat.client;

import com.staringpig.framework.wechat.client.api.miniprogram.*;
import com.staringpig.framework.wechat.client.api.miniprogram.AccountCouponsQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.Code2SessionQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.CreateCouponCommand;
import com.staringpig.framework.wechat.client.api.miniprogram.GenerateUrlLinkCommand;
import com.staringpig.framework.wechat.client.api.miniprogram.GetQrCodeCommand;
import com.staringpig.framework.wechat.client.api.miniprogram.MPAccessTokenQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.OrderInfoQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.OrderListQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.PushCouponCommand;
import com.staringpig.framework.wechat.client.api.miniprogram.SendSubscribedMessageCommand;
import com.staringpig.framework.wechat.ministore.order.OrderQuery;
import feign.Param;
import feign.RequestLine;

/**
 * 小程序客户端
 */
public interface MiniProgramClient {

    String URL = "https://api.weixin.qq.com";

    /**
     * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
     */
    @RequestLine("POST /cgi-bin/com.staringpig.backendframework.message/subscribe/send?access_token={accessToken}")
    SendSubscribedMessageCommand.Result sendMessage(@Param("accessToken") String accessToken,
                                                    SendSubscribedMessageCommand<?> command);

    /**
     * 获取accessToken
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/token?appid={appid}&secret={secret}&grant_type=client_credential")
    MPAccessTokenQuery.Result getAccessToken(@Param("appid") String appId, @Param("secret") String appSecret);

    /**
     * jscode 转 session
     *
     * @return 结果
     */
    @RequestLine("GET /sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code")
    Code2SessionQuery.Result code2Session(@Param("appid") String appId, @Param("secret") String appSecret,
                                          @Param("js_code") String code);

    /**
     * POST https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.get.html
     */
    @RequestLine("POST /wxa/getwxacode?access_token={accessToken}")
    GetQrCodeCommand.Result getQrCodeLimited(@Param("accessToken") String accessToken,
                                             GetQrCodeCommand<?> command);

    /**
     * http请求方式：POST
     * https://api.weixin.qq.com/product/coupon/create?access_token=xxxxxxxxx
     */
    @RequestLine("POST /product/coupon/create?access_token={accessToken}")
    CreateCouponCommand.Result createCoupon(@Param("accessToken") String accessToken, CreateCouponCommand command);

    /**
     * http请求方式：POST
     * https://api.weixin.qq.com/product/coupon/push?access_token=xxxxxxxxx
     */
    @RequestLine("POST /product/coupon/push?access_token={accessToken}")
    PushCouponCommand.Result pushCoupon(@Param("accessToken") String accessToken, PushCouponCommand command);

    /**
     * http请求方式：POST
     * https://api.weixin.qq.com/product/order/get_list?access_token=xxxxxxxxx
     */
    @RequestLine("POST /product/order/get_list?access_token={accessToken}")
    OrderQuery.Result queryOrder(@Param("accessToken") String accessToken, OrderQuery command);

    /**
     * 查询用户优惠券列表
     */
    @RequestLine("POST /product/coupon/get_user?access_token={accessToken}")
    AccountCouponsQuery.Result queryUserCouponLists(@Param("accessToken") String accessToken, AccountCouponsQuery query);

    /**
     * http请求方式：POST
     * https://api.weixin.qq.com/product/order/get_list?access_token=xxxxxxxxx
     */
    @RequestLine("POST /product/order/get_list?access_token={accessToken}")
    OrderListQuery.Result queryOrders(@Param("accessToken") String accessToken, OrderListQuery query);

    /**
     * 查询订单
     */
    @RequestLine("POST /product/order/get?access_token={accessToken}")
    OrderInfoQuery.Result queryOrderInfo(@Param("accessToken") String accessToken, OrderInfoQuery query);

    /**
     * 生成URL Link
     */
    @RequestLine("POST /wxa/generate_urllink?access_token={accessToken}")
    UrlLinkGenerateCommand.Result generateUrlLink(@Param("accessToken") String accessToken, UrlLinkGenerateCommand command);

    @RequestLine("POST /wxa/generate_urllink?access_token={accessToken}")
    GenerateUrlLinkCommand.Result generateUrlLink(@Param("accessToken") String accessToken, GenerateUrlLinkCommand command);
}

package com.staringpig.framework.wechat.client;

import com.staringpig.framework.wechat.client.api.corp.AccessTokenQuery;
import com.staringpig.framework.wechat.client.api.corp.BatchExternalUserQuery;
import com.staringpig.framework.wechat.client.api.corp.ExternalContactListQuery;
import com.staringpig.framework.wechat.client.api.corp.ExternalUserQuery;
import com.staringpig.framework.wechat.client.api.corp.JsapiTicketQuery;
import com.staringpig.framework.wechat.client.api.corp.UserInfo3rdQuery;
import com.staringpig.framework.wechat.client.api.corp.addressBook.DepartmentUserQuery;
import com.staringpig.framework.wechat.client.api.corp.addressBook.UserInfoQuery;
import com.staringpig.framework.wechat.client.api.corp.addressBook.UserQuery;
import com.staringpig.framework.wechat.client.api.corp.external.tag.EditExternalContactTagQuery;
import com.staringpig.framework.wechat.client.api.corp.external.user.ExternalContactRemarkQuery;
import com.staringpig.framework.wechat.client.api.corp.message.TextCardMessageQuery;
import feign.Param;
import feign.RequestLine;

public interface CorpWechatClient {

    String URL = "https://qyapi.weixin.qq.com";

    /**
     * 获取 accessToken
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/gettoken?corpid={corpid}&corpsecret={corpsecret}")
    AccessTokenQuery.Result getAccessToken(@Param("corpid") String corpId, @Param("corpsecret") String secret);

    /**
     * 获取企业 JsapiTicket
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/get_jsapi_ticket?access_token={accessToken}&corpid={corpid}&corpsecret={corpsecret}")
    JsapiTicketQuery.Result getJsapiTicket(@Param("accessToken") String accessToken, @Param("corpid") String corpId, @Param("corpsecret") String secret);

    /**
     * 获取访问用户身份
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/service/getuserinfo3rd?suite_access_token={SUITE_ACCESS_TOKEN}&code={CODE}")
    UserInfo3rdQuery.Result getUserInfo3rd(@Param("SUITE_ACCESS_TOKEN") String suiteAccessToken, @Param("CODE") String code);


    /**
     * 获取客户列表
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/externalcontact/list?access_token={ACCESS_TOKEN}&userid={USERID}")
    ExternalContactListQuery.Result getExternalContactList(@Param("ACCESS_TOKEN") String accessToken, @Param("USERID") String userid);

    /**
     * 获取客户详情
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/externalcontact/get?access_token={ACCESS_TOKEN}&external_userid={EXTERNAL_USERID}&cursor={CURSOR}")
    ExternalUserQuery.Result getExternalContact(@Param("ACCESS_TOKEN") String accessToken, @Param("EXTERNAL_USERID") String externalUserid, @Param("CURSOR") String cursor);

    /**
     * 批量获取客户详情
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/externalcontact/batch/get_by_user?access_token={ACCESS_TOKEN}")
    BatchExternalUserQuery.Result batchGetExternalContact(@Param("ACCESS_TOKEN") String accessToken, BatchExternalUserQuery batchExternalUserQuery);

    /**
     * 获取访问用户身份
     */
    @RequestLine("GET /cgi-bin/user/getuserinfo?access_token={ACCESS_TOKEN}&code={CODE}")
    UserInfoQuery.Result getUserInfo(@Param("ACCESS_TOKEN") String accessToken, @Param("CODE") String code);

    /**
     * 读取成员
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/user/get?access_token={ACCESS_TOKEN}&userid={USERID}")
    UserQuery.Result getUser(@Param("ACCESS_TOKEN") String accessToken, @Param("USERID") String userid);

    /**
     * 获取部门成员
     *
     * @return 结果
     */
    @RequestLine("GET /cgi-bin/user/simplelist?access_token={ACCESS_TOKEN}&department_id={DEPARTMENT_ID}&fetch_child={FETCH_CHILD}")
    DepartmentUserQuery.Result getDepartmentUserList(@Param("ACCESS_TOKEN") String accessToken, @Param("DEPARTMENT_ID") String departmentId, @Param("FETCH_CHILD") String fetchChild);

    /**
     * 编辑客户企业标签
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/externalcontact/mark_tag?access_token={ACCESS_TOKEN}")
    EditExternalContactTagQuery.Result editExternalContactTag(@Param("ACCESS_TOKEN") String accessToken, EditExternalContactTagQuery editExternalContactTagQuery);

    /**
     * 修改客户备注信息
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/externalcontact/remark?access_token={ACCESS_TOKEN}")
    ExternalContactRemarkQuery.Result remarkExternalContact(@Param("ACCESS_TOKEN") String accessToken, ExternalContactRemarkQuery externalContactRemarkQuery);

    /**
     * 发送文本卡片消息
     */
    @RequestLine("POST /cgi-bin/com.staringpig.backendframework.message/send?access_token={ACCESS_TOKEN}")
    TextCardMessageQuery.Result sendTextCardMessage(@Param("ACCESS_TOKEN") String token, TextCardMessageQuery textCardMessageQuery);
}

package com.staringpig.framework.wechat.corp;

import com.staringpig.framework.wechat.client.CorpWechatClient;
import com.staringpig.framework.wechat.client.CorpWechatTokenClient;
import com.staringpig.framework.wechat.client.api.corp.AccessTokenQuery;
import com.staringpig.framework.wechat.client.api.corp.JsapiTicketQuery;
import com.staringpig.framework.wechat.client.api.corp.addressBook.DepartmentUserQuery;
import com.staringpig.framework.wechat.client.api.corp.addressBook.SimpleUser;
import com.staringpig.framework.wechat.client.api.corp.external.tag.EditExternalContactTagQuery;
import com.staringpig.framework.wechat.client.api.corp.external.user.ExternalContactRemarkQuery;
import com.staringpig.framework.wechat.client.api.corp.message.TextCardMessageQuery;

import java.util.List;

/**
 * 远程的企业微信服务实现
 *
 * @author niumy
 */
public class RemoteCorpWechatService implements CorpWechatService {

    private final CorpWechatApplication application;

    private final CorpWechatClient corpWechatClient;

    private final CorpWechatTokenClient corpWechatTokenClient;

    public RemoteCorpWechatService(CorpWechatApplication application, CorpWechatClient corpWechatClient, CorpWechatTokenClient corpWechatTokenClient) {
        this.application = application;
        this.corpWechatClient = corpWechatClient;
        this.corpWechatTokenClient = corpWechatTokenClient;
    }

    /**
     * 获取app的accessToken
     */
    private String fetchAppAccessToken() {
        AccessTokenQuery.Result result = corpWechatTokenClient.getAccessToken(application.getCorpId(), application.getAppSecret());
        result.isOK();
        return result.getAccessToken();
    }


    /**
     * 获取通讯录管理的accessToken
     */
    private String fetchAddressBookAccessToken() {
        AccessTokenQuery.Result result = corpWechatTokenClient.getAccessToken(application.getCorpId(), application.getAddressBookSecret());
        result.isOK();
        return result.getAccessToken();
    }

    /**
     * 获取外部联系人管理的accessToken
     */
    private String fetchExternalAddressBookAccessToken() {
        AccessTokenQuery.Result result = corpWechatTokenClient.getAccessToken(application.getCorpId(), application.getExternalAddressBookSecret());
        result.isOK();
        return result.getAccessToken();
    }

    public String getJsapiTicket() {
        String accessToken = fetchAppAccessToken();
        JsapiTicketQuery.Result result = corpWechatTokenClient.getJsapiTicket(accessToken, application.getCorpId(), application.getAppSecret());
        result.isOK();
        return result.getTicket();
    }

//    public UserVo getUserInfo3rdByCode(String code){
//        String accessToken = fetchAppAccessToken();
//        UserInfo3rdQuery.Result userInfo3rdResult = corpWechatClient.getUserInfo3rd(accessToken, code);
//        userInfo3rdResult.isOK();
//        return getUserById(userInfo3rdResult.getUserId());
//    }
//
//    public UserVo getUserInfoByCode(String code){
//        String accessToken = fetchAppAccessToken();
//        UserInfoQuery.Result userInfoResult = corpWechatClient.getUserInfo(accessToken, code);
//        userInfoResult.isOK();
//        return getUserById(userInfoResult.getUserId());
//    }
//
//    public UserVo getUserById(String userId){
//        String accessToken = fetchAddressBookAccessToken();
//        UserQuery.Result result = corpWechatClient.getUser(accessToken, userId);
//        result.isOK();
//        return CopierUtil.copy(result, UserVo.class);
//    }
//
//    public List<String> getExternalContactList(String userId){
//        String accessToken = fetchExternalAddressBookAccessToken();
//        ExternalContactListQuery.Result result = corpWechatClient.getExternalContactList(accessToken, userId);
//        result.isOK();
//        return result.getExternalUserid();
//    }
//
//    public ExternalUserVo getExternalContact(String externalUserId, String cursor){
//        String accessToken = fetchExternalAddressBookAccessToken();
//        ExternalUserQuery.Result result = corpWechatClient.getExternalContact(accessToken, externalUserId, cursor);
//        result.isOK();
//        return CopierUtil.copy(result.getExternalUser(), ExternalUserVo.class);
//    }
//
//    public List<ExternalContactVo> batchGetExternalContact(BatchExternalUserQuery query){
//        String accessToken = fetchExternalAddressBookAccessToken();
//        BatchExternalUserQuery.Result result = corpWechatClient.batchGetExternalContact(accessToken, query);
//        result.isOK();
//        return CopierUtil.copyList(result.getExternalContactList(), ExternalContactVo.class);
//    }

    public void editExternalContactTag(EditExternalContactTagQuery query) {
        String accessToken = fetchExternalAddressBookAccessToken();
        EditExternalContactTagQuery.Result result = corpWechatClient.editExternalContactTag(accessToken, query);
        result.isOK();
    }

    public List<SimpleUser> getDepartmentUserList(String departmentId, String fetchChild) {
        String accessToken = fetchAddressBookAccessToken();
        DepartmentUserQuery.Result result = corpWechatClient.getDepartmentUserList(accessToken, departmentId, fetchChild);
        result.isOK();
        return result.getUserList();
    }

    public void remarkExternalContact(ExternalContactRemarkQuery query) {
        String accessToken = fetchExternalAddressBookAccessToken();
        ExternalContactRemarkQuery.Result result = corpWechatClient.remarkExternalContact(accessToken, query);
        result.isOK();
    }

    public TextCardMessageQuery.Result sendTextCardMessage(TextCardMessageQuery query) {
        String accessToken = fetchAppAccessToken();
        TextCardMessageQuery.Result result = corpWechatClient.sendTextCardMessage(accessToken, query);
        result.isOK();
        return result;
    }
}

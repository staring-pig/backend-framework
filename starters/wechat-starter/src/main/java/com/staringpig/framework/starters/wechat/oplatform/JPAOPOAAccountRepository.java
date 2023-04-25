package com.staringpig.framework.starters.wechat.oplatform;

import com.staringpig.framework.wechat.offiaccount.OffiAccount;
import com.staringpig.framework.wechat.oplatform.OPOAAccount;
import com.staringpig.framework.wechat.oplatform.OPOAAccountRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JPAOPOAAccountRepository extends OPOAAccountRepository<JPAOPOAAccountRepository.JPAOPOAAccount>,
        CrudRepository<JPAOPOAAccountRepository.JPAOPOAAccount, String> {

    Optional<JPAOPOAAccount> findByOpenId(String openId);

    Optional<JPAOPOAAccount> findByUnionId(String unionId);

    @Override
    default Optional<JPAOPOAAccount> queryByOpenId(String openId) {
        return findByOpenId(openId);
    }

    @Override
    default Optional<JPAOPOAAccount> queryByUnionId(String unionId) {
        return findByUnionId(unionId);
    }

    @Override
    default JPAOPOAAccount saveByUserInfo(String appId, OffiAccount.UserInfo userInfo) {
        JPAOPOAAccount account = new JPAOPOAAccount(userInfo.getOpenid(), appId, userInfo.getUnionId());
        account.setCity(userInfo.getCity());
        account.setCountry(userInfo.getCountry());
        account.setLanguage(userInfo.getLanguage());
        account.setNickname(userInfo.getNickname());
        account.setProvince(userInfo.getProvince());
        account.setGroupId(userInfo.getGroupId());
        account.setHeadImgUrl(userInfo.getHeadImgUrl());
        account.setQrScene(userInfo.getQrScene());
        account.setQrSceneStr(userInfo.getQrSceneStr());
        account.setRemark(userInfo.getRemark());
        account.setSex(userInfo.getSex());
        account.setSubscribeScene(userInfo.getSubscribeScene());
        account.setSubscribeTime(userInfo.getSubscribeTime());
        account.setTagIds(userInfo.getTagIds());
        save(account);
        return account;
    }

    @Setter
    @Getter
    @Entity
    @Table(name = "t_op_oa_account")
    class JPAOPOAAccount extends OPOAAccount {

        @Id
        @GeneratedValue
        private Long id;

        public JPAOPOAAccount(String openId, String oaAppId, String unionId) {
            super(openId, oaAppId, unionId);
        }
    }
}

package com.staringpig.framework.starters.wechat.offiaccount;

import com.staringpig.framework.wechat.offiaccount.OffiAccount;
import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import com.staringpig.framework.wechat.offiaccount.account.OAAccountRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JPAOAAccountRepository extends OAAccountRepository<JPAOAAccountRepository.JPAOAAccount>,
        CrudRepository<JPAOAAccountRepository.JPAOAAccount, String> {

    Optional<JPAOAAccount> findByOpenId(String openId);

    @Override
    default Optional<JPAOAAccount> queryByOpenId(String openId) {
        return findByOpenId(openId);
    }

    @Override
    default JPAOAAccount saveByUserInfo(String appId, OffiAccount.UserInfo userInfo) {
        JPAOAAccount account = new JPAOAAccount(userInfo.getOpenid(), appId);
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

    @Entity
    @Setter
    @Getter
    @Table(name = "t_oa_account")
    class JPAOAAccount extends OAAccount {

        @Id
        @GeneratedValue
        private Long id;

        public JPAOAAccount(String openId, String oaAppId) {
            super(openId, oaAppId);
        }
    }
}

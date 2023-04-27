package com.staringpig.framework.starters.wechat.offiaccount;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import com.staringpig.framework.wechat.offiaccount.user.OAUserRepository;
import com.staringpig.framework.wechat.oplatform.OPUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface JPAOAUserRepository extends OAUserRepository,
        CrudRepository<JPAOAUserRepository.JPAOAUserEntity, UUID> {

    Optional<JPAOAUserEntity> findByOpenId(String openId);

    @Override
    default Optional<OAUser> queryByOpenId(String openId) {
        return findByOpenId(openId).map(JPAOAUserEntity::convert);
    }

    @Override
    default OAUser saveIt(OAUser user) {
        return save(JPAOAUserEntity.valueOf(user)).convert();
    }


    @Entity
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "t_oa_user")
    class JPAOAUserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
        /**
         * 对应用户id
         */
        private String openId;
        /**
         * 平台用户ID
         */
        private String unionId;

        /**
         * 公众号的appId
         */
        private String oaAppId;
        /**
         * 是否订阅
         */
        private Boolean subscribed;
        /**
         * 昵称
         */
        private String nickname;
        /**
         * 性别
         */
        private Integer sex;
        /**
         * 语言
         */
        private String language;
        /**
         * 城市
         */
        private String city;
        /**
         * 地区
         */
        private String province;
        /**
         * 国家
         */
        private String country;
        /**
         * 头像
         */
        private String headImgUrl;
        /**
         * 监听时间
         */
        private Integer subscribeTime;
        /**
         * 标记
         */
        private String remark;
        /**
         * 组ID
         */
        private Integer groupId;
        /**
         * 订阅场景
         */
        private String subscribeScene;
        /**
         * 二维码
         */
        private Integer qrScene;
        /**
         * 二维码内容
         */
        private String qrSceneStr;

        public OAUser convert() {
            OAUser account = new OAUser(id, this.openId, this.oaAppId, new OPUser(this.unionId));
            account.setCity(this.getCity());
            account.setCountry(this.getCountry());
            account.setLanguage(this.getLanguage());
            account.setNickname(this.getNickname());
            account.setProvince(this.getProvince());
            account.setGroupId(this.getGroupId());
            account.setHeadImgUrl(this.getHeadImgUrl());
            account.setQrScene(this.getQrScene());
            account.setQrSceneStr(this.getQrSceneStr());
            account.setRemark(this.getRemark());
            account.setSex(this.getSex());
            account.setSubscribed(this.getSubscribed());
            account.setSubscribeScene(this.getSubscribeScene());
            account.setSubscribeTime(this.getSubscribeTime());
            return account;
        }

        public static JPAOAUserEntity valueOf(OAUser user) {
            return JPAOAUserEntity.builder()
                    .id(user.getId())
                    .unionId(user.getOpUser().unionId())
                    .city(user.getCity())
                    .sex(user.getSex())
                    .qrScene(user.getQrScene())
                    .country(user.getCountry())
                    .groupId(user.getGroupId())
                    .headImgUrl(user.getHeadImgUrl())
                    .language(user.getLanguage())
                    .nickname(user.getNickname())
                    .oaAppId(user.getOaAppId())
                    .openId(user.getOpenId())
                    .province(user.getProvince())
                    .qrSceneStr(user.getQrSceneStr())
                    .remark(user.getRemark())
                    .subscribed(user.getSubscribed())
                    .subscribeScene(user.getSubscribeScene())
                    .subscribeTime(user.getSubscribeTime())
                    .build();
        }
    }
}

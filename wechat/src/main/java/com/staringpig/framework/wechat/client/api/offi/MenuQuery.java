package com.staringpig.framework.wechat.client.api.offi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.WechatServerException;
import com.staringpig.framework.wechat.offiaccount.menu.BaseButton;
import com.staringpig.framework.wechat.offiaccount.menu.ClickButton;
import com.staringpig.framework.wechat.offiaccount.menu.EmptyButton;
import com.staringpig.framework.wechat.offiaccount.menu.MediaIdButton;
import com.staringpig.framework.wechat.offiaccount.menu.MiniProgramButton;
import com.staringpig.framework.wechat.offiaccount.menu.OffiAccountMenu;
import com.staringpig.framework.wechat.offiaccount.menu.ViewButton;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dreamlu.mica.core.utils.$;

import java.util.List;

/**
 * @author ldh
 * time 2021-11-23 13:26
 */
public class MenuQuery {

    @NoArgsConstructor
    @Data
    public static class Result {

        @JsonProperty("is_menu_open")
        private Integer isMenuOpen;
        @JsonProperty("selfmenu_info")
        private SelfmenuInfoDTO selfMenuInfo;

        public void isOK() {
            if (isMenuOpen == null || $.isEmpty(selfMenuInfo)) {
                throw new WechatServerException("公众号菜单获取失败，请检查");
            }
        }

        public OffiAccountMenu getOffiAccountMenu() {
            OffiAccountMenu offiAccountMenu = new OffiAccountMenu();
            for (SelfmenuInfoDTO.ButtonDTO buttonDTO : this.selfMenuInfo.getButton()) {
                offiAccountMenu.add(generateSingleButton(buttonDTO));
            }
            return offiAccountMenu;
        }

        private BaseButton generateSingleButton(SelfmenuInfoDTO.ButtonDTO buttonDTO) {
            BaseButton result = null;
            if (buttonDTO.getType() == null) {
                return new EmptyButton();
            }
            switch (BaseButton.ButtonType.valueOf(buttonDTO.getType())) {
                case click:
                    ClickButton clickButton = new ClickButton();
                    clickButton.setKey(buttonDTO.getKey());
                    clickButton.setName(buttonDTO.getName());
                    result = clickButton;
                    break;
                case view:
                    ViewButton viewButton = new ViewButton();
                    viewButton.setUrl(buttonDTO.getUrl());
                    viewButton.setName(buttonDTO.getName());
                    result = viewButton;
                    break;
                case miniprogram:
                    MiniProgramButton miniProgramButton = new MiniProgramButton();
                    miniProgramButton.setPagePath(buttonDTO.getPagePath());
                    miniProgramButton.setAppId(buttonDTO.getAppId());
                    miniProgramButton.setUrl(buttonDTO.getUrl());
                    miniProgramButton.setName(buttonDTO.getName());
                    result = miniProgramButton;
                    break;
                case media_id:
                    MediaIdButton mediaIdButton = new MediaIdButton();
                    mediaIdButton.setMediaId(buttonDTO.getMediaId());
                    mediaIdButton.setName(buttonDTO.getName());
                    result = mediaIdButton;
                    break;
                default:
                    //todo 其余类型的处理
                    break;
            }
            return result;
        }

        @NoArgsConstructor
        @Data
        public static class SelfmenuInfoDTO {
            @JsonProperty("button")
            private List<ButtonDTO> button;

            @NoArgsConstructor
            @Data
            public static class ButtonDTO {
                @JsonProperty("type")
                private String type;
                @JsonProperty("name")
                private String name;
                @JsonProperty("key")
                private String key;
                @JsonProperty("media_id")
                private String mediaId;
                @JsonProperty("url")
                private String url;
                @JsonProperty("appid")
                private String appId;
                @JsonProperty("pagepath")
                private String pagePath;
                @JsonProperty("sub_button")
                private SubButtonDTO subButton;

                @NoArgsConstructor
                @Data
                public static class SubButtonDTO {
                    @JsonProperty("list")
                    private List<ListDTO> list;

                    @NoArgsConstructor
                    @Data
                    public static class ListDTO {
                        @JsonProperty("type")
                        private String type;
                        @JsonProperty("name")
                        private String name;
                        @JsonProperty("url")
                        private String url;
                        @JsonProperty("key")
                        private String key;
                        @JsonProperty("media_id")
                        private String mediaId;
                        @JsonProperty("appid")
                        private String appId;
                        @JsonProperty("pagepath")
                        private String pagePath;
                    }
                }
            }
        }
    }
}

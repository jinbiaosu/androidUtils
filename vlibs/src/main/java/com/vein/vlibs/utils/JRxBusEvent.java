package com.vein.vlibs.utils;

import com.vein.vlibs.data.model.UserInfoModel;

public class JRxBusEvent {

    public static class UserInfoEvent {
        public UserInfoModel userInfoModel;

        public UserInfoEvent(UserInfoModel userInfoModel) {
            this.userInfoModel = userInfoModel;
        }
    }
}

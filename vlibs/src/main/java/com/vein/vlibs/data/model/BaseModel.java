package com.vein.vlibs.data.model;

/**
 * BaseModel
 * Created by 董旭 16/5/24 上午10:21
 * Copyright (c) 2014 小云社群. All rights reserved
 */
public class BaseModel {
    public int rs;
    public String errcode;
    public HeaderModel head;

    public static class HeaderModel{
        public String errCode;
        public String errInfo;
        public String version;
        public int alert;

        @Override
        public String toString() {
            return "HeaderModel{" +
                    "errCode='" + errCode + '\'' +
                    ", errInfo='" + errInfo + '\'' +
                    ", version='" + version + '\'' +
                    ", alert=" + alert +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "rs=" + rs +
                ", errcode='" + errcode + '\'' +
                ", head=" + head.toString() +
                '}';
    }
}

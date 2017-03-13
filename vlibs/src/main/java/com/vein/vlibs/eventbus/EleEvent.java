package com.vein.vlibs.eventbus;

/**
 * Created by htjc on 17/3/2.
 */

public class EleEvent {
    public static class PushEvent {

        private  String tag;
        public PushEvent(String tag) {
            this.tag = tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
    }
}

package com.vein.vlibs.singleP;

/**
 * Created by vein on 17/3/12.
 */

public class SigleMethod2 {


    private static class HelperHolder {
        public static final SigleMethod2 helper = new SigleMethod2();
    }

    public static SigleMethod2 getHelper() {
        return HelperHolder.helper;
    }

}

package com.vein.vlibs.singleP;

/**
 * Created by vein on 17/3/12.
 */

public class SingleMethod1 {
    private static volatile SingleMethod1 mHelper = null;

    public static SingleMethod1 getInstance() {
        SingleMethod1 helper = mHelper;
        if (helper == null) {
            synchronized (SingleMethod1.class) {
                helper = mHelper;
                if (helper == null) {
                    helper = new SingleMethod1();
                    helper = mHelper;
                }
            }
        }
        return helper;

    }
}

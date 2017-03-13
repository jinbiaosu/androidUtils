package com.vein.vlibs.db.sp;

import android.content.Context;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by vein on 17/3/12.
 */

public class SharePreferenceUtils {
    private static volatile SharePreferenceUtils mHelper = null;

    private Context context;

    public static SharePreferenceUtils getInstance(Context context) {
        SharePreferenceUtils helper = mHelper;
        if (helper == null) {
            synchronized (SharePreferenceUtils.class) {
                helper = mHelper;
                if (helper == null) {
                    helper = new SharePreferenceUtils(context);
                    helper = mHelper;
                }
            }
        }
        return helper;
    }

    public SharePreferenceUtils(Context context) {
        this.context = context;

    }

    public RxSharedPreferences getSharePerferences() {
        if (context == null) {
            return null;
        }
        return RxSharedPreferences.create(getDefaultSharedPreferences(context));
    }


}

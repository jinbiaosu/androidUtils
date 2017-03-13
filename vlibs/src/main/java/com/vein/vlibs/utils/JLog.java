package com.vein.vlibs.utils;

import android.os.Build;
import android.util.Log;

public class JLog {

    public static void v(String tag, String msg) {
        if (isPrintLog())
            Log.v(tag, getLogTag(tag) + msg);
    }

    public static void d(String tag, String msg) {
        if (isPrintLog())
            Log.d(tag, getLogTag(tag) + msg);
    }

    public static void i(String tag, String msg) {
        if (isPrintLog()) {
            Log.i(tag, getLogTag(tag) + msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isPrintLog()) {
            Log.w(tag, getLogTag(tag) + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isPrintLog()) {
            Log.e(tag, getLogTag(tag) + msg);
        }
    }

    public static void e(String tag, Exception e) {
        String error = null;
        StackTraceElement[] stacks = e.getStackTrace();
        if (stacks.length > 0) {
            error = stacks[1].getClassName() + "\t" + stacks[1].getMethodName() + "\t" + stacks[1].getLineNumber();
        }
        if (error == null || "".equals(error)) {
            error = e.toString();
        }
        e(tag, error);
    }

    private static String getLogTag(String tag) {
        if (Build.VERSION.SDK_INT > 15) {
            return "### SQLog >> ";
        } else {
            return "";
        }
    }

    public static boolean isPrintLog() {
        return true;
    }
}

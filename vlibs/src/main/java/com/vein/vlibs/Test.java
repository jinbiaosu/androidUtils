package com.vein.vlibs;

import android.text.TextUtils;

/**
 * Created by htjc on 17/1/18.
 */

public class Test {
    public static void main(String [] args){
        System.out.println(strFen2strYuan("1"));
        System.out.println(strFen2strYuan("12"));
        System.out.println(strFen2strYuan("123"));

        System.out.println(strFen2strYuan("-1"));
        System.out.println(strFen2strYuan("-12"));
        System.out.println(strFen2strYuan("-123"));

    }
    public static String strFen2strYuan(String strFen) {
        String reslut = "0.00";
        boolean isPositiveInt;
//        if (TextUtils.isEmpty(strFen)) {
//            return reslut;
//        }
        if (strFen.startsWith("-")) {
            isPositiveInt = false;
            strFen = strFen.substring(1);
        } else {
            isPositiveInt = true;
        }

        int length = strFen.length();
        if (length == 1) {
            reslut = "0.0" + strFen;
        } else if (length == 2) {
            reslut = "0." + strFen;
        } else if (length > 2) {
            reslut = strFen.substring(0, length - 2) + "." + strFen.substring(length - 2, length);
        }
        if (!isPositiveInt){
            reslut="-"+reslut;
        }
        return reslut;
    }
}

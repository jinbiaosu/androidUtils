package com.vein.vlibs.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.lang.reflect.Field;

public class JResource {

    private static final String TAG = JResource.class.getSimpleName();

    private static JResource sResource;
    private static String mPackage;
    private Resources mResources;

    private JResource(Context context) {
        mPackage = context.getPackageName();
        mResources = context.getResources();
    }

    public static JResource getInstance(Context context) {

        if (sResource == null){
            synchronized (JResource.class){
                if (sResource == null){
                    sResource = new JResource(context.getApplicationContext());
                }
            }
        }
        return sResource;
    }

    /**
     * 获取String资源值
     * @param name 资源ID
     * @return value
     */
    public String getString(String name){
        String value = "";
        try {
            int id = mResources.getIdentifier(name, "string", mPackage);
            value = mResources.getString(id);
        }catch (Exception e){
            Log.e(TAG, "getString: resource identifier not found");
        }
        return value;
    }

    /**
     * 获取Color资源值
     * @param name 资源ID
     * @return value
     */
    public int getColor(String name){
        int value = Color.TRANSPARENT;
        try {
            int id = mResources.getIdentifier(name, "color", mPackage);
            value = mResources.getColor(id);
        }catch (Exception e){
            Log.e(TAG, "getColor: resource identifier not found");
        }
        return value;
    }

    /**
     * 获取Dimension资源值
     * @param name 资源ID
     * @return value
     */
    public float getDimension(String name){
        float value = 0;
        try {
            int id = mResources.getIdentifier(name, "dimen", mPackage);
            value = mResources.getDimension(id);
        }catch (Exception e){
            Log.e(TAG, "getDimension: resource identifier not found");
        }
        return value;
    }

    /**
     * 获取Style资源值
     * @param name 资源ID
     * @return value
     */
    public int getStyleId(String name){
        int id = 0;
        try {
            id = mResources.getIdentifier(name, "style", mPackage);
        }catch (Exception e){
            Log.e(TAG, "getStyleId: resource identifier not found");
        }
        return id;
    }

    /**
     * 获取Layout资源值
     * @param name 资源ID
     * @return value
     */
    public int getLayoutId(String name){
        int id = 0;
        try {
            id = mResources.getIdentifier(name, "layout", mPackage);
        }catch (Exception e){
            Log.e(TAG, "getLayoutId: resource identifier not found");
        }
        return id;
    }

    /**
     * 获取View资源值
     * @param name 资源ID
     * @return value
     */
    public int getViewId(String name){
        int id = 0;
        try {
            id = mResources.getIdentifier(name, "id", mPackage);
        }catch (Exception e){
            Log.e(TAG, "getViewId: resource identifier not found");
        }
        return id;
    }

    /**
     * 获取Drawable资源值
     * @param name 资源ID
     * @return value
     */
    public int getDrawableId(String name){
        int id = 0;
        try {
            id = mResources.getIdentifier(name, "drawable", mPackage);
        }catch (Exception e){
            Log.e(TAG, "getDrawableId: resource identifier not found");
        }
        return id;
    }

    /**
     * 获取Drawable资源值
     * @param name 资源ID
     * @return value
     */
    public Drawable getDrawable(String name){
        Drawable value = null;
        try {
            int id = getDrawableId(name);
            value = mResources.getDrawable(id);
        }catch (Exception e){
            Log.e(TAG, "getDrawable: resource identifier not found");
        }
        return value;
    }

    /**
     * 获取到styleable的数据
     * @param name 资源ID
     * @return value
     */
    public int getStyleable(String name) {
        Integer integer = (Integer) getResourceId(name, "styleable");
        if (integer == null){
            return 0;
        }else {
            return integer.intValue();
        }
    }

    /**
     * 获取styleable的ID号数组
     * @param name 资源ID
     * @return value
     */
    public int[] getStyleableArray(String name) {
        return (int[]) getResourceId(name, "styleable");
    }

    /**
     * 获取资源
     * @param name 资源ID
     * @param type 资源类型
     * @return value
     */
    private Object getResourceId(String name, String type) {
        String className = mPackage + ".R";
        try {
            Class cls = Class.forName(className);
            for (Class childClass : cls.getClasses()) {
                String simple = childClass.getSimpleName();
                if (simple.equals(type)) {
                    for (Field field : childClass.getFields()) {
                        String fieldName = field.getName();
                        if (fieldName.equals(name)) {
                            System.out.println(fieldName);
                            return field.get(null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

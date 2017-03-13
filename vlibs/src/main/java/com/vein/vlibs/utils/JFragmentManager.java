package com.vein.vlibs.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;


public class JFragmentManager {

    /**
     * 添加fragment
     * @param fragmentManager manager
     * @param fragment  要添加的fragment
     * @param frameId   容器
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();

    }

    /**
     * 切换fragment
     * @param fragmentManager manager
     * @param toFragment 切换到fragment
     * @param TAG   切换到TAG
     * @param frameId   容器
     */
    public static void switchFragment (@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment toFragment, String TAG, int frameId) {
        Fragment currentFragment = null; //当前展示的fragment
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (int i = 0; i < fragments.size(); i++) {
                if (fragments.get(i) != null && fragments.get(i).isVisible()) {
                    currentFragment = fragments.get(i);
                }
            }
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        //判断当前TAG是否存在，如果存在则不加载新的fragment
        if (fragment != null){
            toFragment = fragment;
        }
        //判断currentFragment的存在来决定是否hide
        //hide和show不会调用fragment的onpause和onresume方法，需要fragment通过onHiddenChanged来实现
        if (currentFragment == null){
            if (toFragment.isAdded()){
                transaction.show(toFragment);
            }else {
                transaction.add(frameId, toFragment, TAG);
            }
        }else{
            if (toFragment.isAdded()){
                transaction.hide(currentFragment).show(toFragment);
            }else{
                transaction.hide(currentFragment).add(frameId, toFragment, TAG);
            }
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

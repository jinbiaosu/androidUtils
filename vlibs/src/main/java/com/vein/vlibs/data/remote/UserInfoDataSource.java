package com.vein.vlibs.data.remote;

import com.vein.vlibs.data.model.UserInfoModel;
import com.vein.vlibs.data.remote.base.BaseClientDataSource;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by htjc on 17/1/17.
 */

public class UserInfoDataSource extends BaseClientDataSource {

    private UserInfoSerivice mService;

    @Override
    protected void initDataSource(Retrofit retrofit) {
        mService = retrofit.create(UserInfoSerivice.class);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public Observable<UserInfoModel> getUserInfo() {
        return mService.getUserInfo();
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }


}

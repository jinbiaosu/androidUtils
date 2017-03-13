package com.vein.vlibs.data.remote;


import com.vein.vlibs.data.model.UserInfoModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by htjc on 17/1/17.
 */

public interface UserInfoSerivice {
    @GET("index.php")
    Observable<UserInfoModel>  getUserInfo();
}

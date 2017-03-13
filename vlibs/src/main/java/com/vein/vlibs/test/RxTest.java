package com.vein.vlibs.test;

import com.vein.vlibs.data.model.UserInfoModel;
import com.vein.vlibs.data.remote.UserInfoDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;
import rx.observers.Observers;

/**
 * Created by htjc on 17/1/19.
 */

public class RxTest {
    public  static void main(String [] args){

        System.out.print("************************");

        RxTest test=new RxTest();
        test.getUserInfo(test.getUserInfoByLocal(),test.getUserInfoByNet()).subscribe(new Subscriber<UserInfoModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(UserInfoModel userInfoModel) {
                    System.out.print(userInfoModel.getName()+"\n");
            }
        });
    }

    private UserInfoModel getUserInfoByLocal1() {
        UserInfoModel model = new UserInfoModel();
        model.setId(0000l);
        model.setName("vein");
        model.setAge(30);
        model.setDesc("local");
        return model;
    }


    private Observable<UserInfoModel> getUserInfoByNet(){
        UserInfoDataSource dataSource=new UserInfoDataSource();
        return  dataSource.getUserInfo();
    }



    public Observable<UserInfoModel>  getUserInfoByLocal(){
            return  Observable.fromCallable(new Callable<UserInfoModel>() {
                @Override
                public UserInfoModel call() throws Exception {
                    return getUserInfoByLocal1();
                }
            });
    }

    public Observable<UserInfoModel> getUserInfo(Observable<UserInfoModel> observable1,Observable<UserInfoModel>  observable2){
        return Observable.merge(observable1,observable2);
    }

}

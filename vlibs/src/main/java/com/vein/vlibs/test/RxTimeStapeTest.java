//package com.vein.vlibs.test;
//
//import com.vein.vlibs.data.model.UserInfoModel;
//import com.vein.vlibs.data.remote.UserInfoDataSource;
//
//import java.util.concurrent.Callable;
//
//import rx.Observable;
//import rx.Subscriber;
//import rx.functions.Action1;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;
//import rx.schedulers.Timestamped;
//
///**
// * Created by htjc on 17/1/19.
// */
//
//public class RxTimeStapeTest {
//
//   static ITimestampedView view = new ITimestampedView() {
//        @Override
//        public long getViewDataTimeMillis() {
//            return 0l;
//        }
//    };
//
//    public static  void main(String[] args) {
//
//        RxTimeStapeTest timeStapeTest = new RxTimeStapeTest();
//        timeStapeTest.getMergedUserInfo(view).subscribe(new Subscriber<Timestamped<UserInfoModel>>() {
//            @Override
//            public void onCompleted() {
//                System.out.print("onCompleted\n");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//                System.out.print("onError\n");
//            }
//
//            @Override
//            public void onNext(Timestamped<UserInfoModel> userInfoModelTimestamped) {
//                System.out.print("onNext\n");
//                System.out.print("####################\n");
//                System.out.print(userInfoModelTimestamped.getTimestampMillis() + userInfoModelTimestamped.getValue().getName() + "\n");
//                System.out.print("####################\n");
//            }
//        });
//    }
//
//
//    private void saveUserInfo(Timestamped<UserInfoModel> userInfoModelTimestamped) {
//
//
//    }
//
//    private Observable<Timestamped<UserInfoModel>> getUserInfoFormLocal() {
//        return Observable.fromCallable(new Callable<Timestamped<UserInfoModel>>() {
//            @Override
//            public Timestamped<UserInfoModel> call() throws Exception {
//                UserInfoModel userInfoModel = new UserInfoModel();
//                userInfoModel.setAge(29);
//                userInfoModel.setId(100l);
//                userInfoModel.setDesc("hellow rx");
//                userInfoModel.setName("vein");
//                userInfoModel.setTime(System.currentTimeMillis());
//                return new Timestamped<UserInfoModel>(userInfoModel.getTime(), userInfoModel);
//            }
//        });
//
//    }
//
//    private Observable<UserInfoModel> getUserInfoFormNet() {
//        return new UserInfoDataSource().getUserInfo();
//    }
//
//
//    /**
//     * 过滤操作, meiziModelTimestamped.getTimestampMillis()表示从网络下载时的时间戳,
//     * timestampedView.getViewDataTimeMillis()表示本地读取文件的时间戳.
//     * 如果网络时间戳大于本地文件时间戳, 则说明网络下载的数据较新, 不应该被拦截.
//     *
//     * @param
//     * @return
//     */
//    private Func1<? super Timestamped<UserInfoModel>, Boolean> getRecentUserInfoFilter(final ITimestampedView viewTime) {
//        return new Func1<Timestamped<UserInfoModel>, Boolean>() {
//            @Override
//            public Boolean call(Timestamped<UserInfoModel> userModel) {
//                return userModel != null
//                        && userModel.getValue() != null
//                        && userModel.getTimestampMillis() >= viewTime.getViewDataTimeMillis() ;
//            }
//        };
//    }
//
//
//    /**
//     * 从多个数据源获取数据
//     *
//     * @return
//     */
//    public Observable<Timestamped<UserInfoModel>> getMergedUserInfo(ITimestampedView viewTime) {
//        return Observable.mergeDelayError(getUserInfoFormLocal().subscribeOn(Schedulers.io()),
//                getUserInfoFormNet().timestamp().doOnNext(new Action1<Timestamped<UserInfoModel>>() {
//                    @Override
//                    public void call(Timestamped<UserInfoModel> timestamped) {
//
//                        System.out.print("*********11111*********"+"\n");
//                        System.out.print(timestamped.getTimestampMillis() + timestamped.getValue().getName() + "\n");
//                        System.out.print("**********222222********\n");
//
//                    }
//                }).subscribeOn(Schedulers.io())).onErrorReturn(new Func1<Throwable, Timestamped<UserInfoModel>>() {
//            @Override
//            public Timestamped<UserInfoModel> call(Throwable throwable) {
//                throwable.printStackTrace();
//                System.err.print("onErrorReturn");
//                return null;
//            }
//        }).filter(getRecentUserInfoFilter(viewTime));
//    }
//}

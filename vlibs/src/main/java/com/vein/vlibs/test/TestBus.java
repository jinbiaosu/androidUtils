package com.vein.vlibs.test;

import com.vein.vlibs.eventbus.EleEvent;
import com.vein.vlibs.eventbus.EleEventBus;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by htjc on 17/3/2.
 */

public class TestBus {
    private static Observable<EleEvent.PushEvent> observable;

    private static void registerObservable() {
        observable = EleEventBus.getInstance().register(EleEvent.PushEvent.class);
        observable.subscribe(new Action1<EleEvent.PushEvent>() {
            @Override
            public void call(EleEvent.PushEvent event) {
                System.out.print(event.getTag());
            }
        });
    }

    private void unRegisterObservable() {
        if (observable != null) {
            EleEventBus.getInstance().unregister(EleEvent.PushEvent.class, observable);
        }
    }

    public static void main(String[] args) {
        registerObservable();
        EleEventBus.getInstance().post(new EleEvent.PushEvent("aaaaa"));
    }
}

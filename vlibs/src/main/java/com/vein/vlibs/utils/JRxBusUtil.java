package com.vein.vlibs.utils;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class JRxBusUtil {

    private static volatile JRxBusUtil defaultInstance;
    private final Subject<Object, Object> bus;
    public JRxBusUtil() {
        bus = new SerializedSubject<Object, Object>(PublishSubject.create());
    }

    public static JRxBusUtil getDefault() {
        JRxBusUtil JRxBusUtil = defaultInstance;
        if (defaultInstance == null) {
            synchronized (JRxBusUtil.class) {
                JRxBusUtil = defaultInstance;
                if (defaultInstance == null) {
                    JRxBusUtil = new JRxBusUtil();
                    defaultInstance = JRxBusUtil;
                }
            }
        }
        return JRxBusUtil;
    }

    public void post (Object o) {
        bus.onNext(o);
    }

    public <T> Observable<T> toObserverable (final Class<T> eventType) {
        return bus.filter(new Func1<Object, Boolean>() {
            @Override
            public Boolean call(Object o) {
                return eventType.isInstance(o);
            }
        }) .cast(eventType);
    }

}

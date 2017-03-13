package com.vein.vlibs.eventbus;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by htjc on 17/3/2.
 */

public class EleEventBus {
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();
    private static volatile EleEventBus instance;

    private EleEventBus() {
    }

    public static EleEventBus getInstance() {
        EleEventBus inst = instance;
        if (inst == null) {
            synchronized (EleEventBus.class) {
                inst = instance;
                if (inst == null) {
                    inst = new EleEventBus();
                    instance = inst;
                }
            }
        }
        return inst;
    }
    /**
     * 注册
     *
     * @param tag
     * @return
     */
    public <T> Observable<T> register(@NonNull Class<T> tag) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }
        Subject<T, T> subject = PublishSubject.create();
        subjectList.add(subject);
        return subject;
    }

    /**
     * 解除注册
     *
     * @param tag
     */
    public <T> void unregister(@NonNull Class<T> tag, @NonNull Observable observable) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove(observable);
            if (subjects.isEmpty()) {
                subjectMapper.remove(tag);
            }
        }
    }


    /**
     * 发送消息
     *
     * @param event
     */
    public <T> void post(@NonNull Object event) {
        List<Subject> subjectList = subjectMapper.get(event.getClass());
        if (subjectList != null && !subjectList.isEmpty()) {
            for (Subject subject : subjectList) {
                subject.onNext(event);
            }
        }
    }

    /**
     * 清除订阅
     */
    public void clear() {
        if (subjectMapper.isEmpty()) {
            return;
        }
        subjectMapper.clear();
    }
}

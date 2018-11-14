package com.github.zahileoo.livedatademo.observer;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * Activity生命周期监听，放一些非UI的业务逻辑
 * Created by zahi on 2018/11/9.
 */
public class CustomLifecycleObserver implements LifecycleObserver {

    private static final String TAG = "CustomLifecycleObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        Log.d(TAG, "----------onCreate---------");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        Log.d(TAG, "----------onStart---------");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        Log.d(TAG, "----------onResume---------");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        Log.d(TAG, "----------onPause---------");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        Log.d(TAG, "----------onStop---------");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        Log.d(TAG, "----------onDestroy---------");
    }
}

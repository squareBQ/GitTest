package com.github.zahileoo.livedatademo.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by zahi on 2018/11/9.
 */
public class TextViewModel<T> extends ViewModel {

    private MutableLiveData<T> mData = new MutableLiveData<>();

    public TextViewModel() {
        setData(null);
    }

    public void setData(T data) {
        mData.setValue(data);
    }

    public MutableLiveData<T> getData() {
        return mData;
    }
}

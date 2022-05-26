package com.example.days6.ui.dev;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DevViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DevViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Dev fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
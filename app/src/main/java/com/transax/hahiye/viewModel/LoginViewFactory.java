package com.transax.hahiye.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.transax.hahiye.callback.ResultCallback;

public class LoginViewFactory extends ViewModelProvider.NewInstanceFactory {
    private ResultCallback loginResultCallback;

    public LoginViewFactory(ResultCallback loginResultCallback) {
        this.loginResultCallback = loginResultCallback;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(loginResultCallback);
    }
}

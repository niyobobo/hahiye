package rw.transax.hahiye.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import rw.transax.hahiye.callback.ResultCallback;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private ResultCallback loginResultCallback;

    public LoginViewModelFactory(ResultCallback loginResultCallback) {
        this.loginResultCallback = loginResultCallback;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new LoginViewModel(loginResultCallback);
    }
}

package com.transax.hahiye.viewModel;

import android.arch.lifecycle.ViewModel;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.transax.hahiye.callback.ResultCallback;
import com.transax.hahiye.model.UserModel;

public class LoginViewModel extends ViewModel {

    private ResultCallback resultCallback;
    private UserModel userModel = new UserModel();

    LoginViewModel(ResultCallback resultCallback) {
        this.resultCallback = resultCallback;
    }

    public TextWatcher getEmailFromView() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userModel.setEmail(s.toString());
            }
        };
    }

    public TextWatcher getPasswordFromView() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userModel.setPassword(s.toString());
            }
        };
    }

    public void performLogin(View view) {
        if (userModel.isValidData())
            resultCallback.onSuccess("Information are correct");
        else
            resultCallback.onError("Provide correct information");
    }
}

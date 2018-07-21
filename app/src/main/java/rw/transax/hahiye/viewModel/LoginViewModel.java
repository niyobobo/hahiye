package rw.transax.hahiye.viewModel;

import android.arch.lifecycle.ViewModel;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import rw.transax.hahiye.callback.ResultCallback;
import rw.transax.hahiye.model.UserModel;

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

        switch (userModel.isValidData()) {
            case 0:
                resultCallback.onError("Provide both email and password");
                break;
            case 1:
                resultCallback.onError("Provide your email");
                break;
            case 2:
                resultCallback.onError("Provide your password");
                break;
            case 3:
                resultCallback.onError("You entered an incorrect email");
                break;
            case 4:
                resultCallback.onError("Password should be 6 characters long");
                break;
            case -1:
                resultCallback.onSuccess("Success");
        }
    }
}

package rw.transax.hahiye.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.Presenter;
import rw.transax.hahiye.databinding.ActivityLoginBinding;
import rw.transax.hahiye.utils.AppUtils;
import rw.transax.hahiye.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements Presenter {

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        activityBinding.setViewModel(loginViewModel);
        activityBinding.setPresenter(this);
        activityBinding.executePendingBindings();
    }

    @Override
    public void performLogin() {

        switch (loginViewModel.validatingData()) {
            case 0:
                AppUtils.showToast(this, "Provide both email and password");
                break;
            case 1:
                AppUtils.showToast(this, "Provide your email");
                break;
            case 2:
                AppUtils.showToast(this, "Provide your password");
                break;
            case 3:
                AppUtils.showToast(this, "Email is not valid");
                break;
            case 4:
                AppUtils.showToast(this, "Password should be 6 characters long");
                break;
            case -1:
                loginViewModel.userLogin();
        }
    }

    @Override
    public void backPressed() {
        super.onBackPressed();
    }

    @Override
    public void forgetPassword() {
        /* Start activity to request new password  */
        Toast.makeText(this, "Forget password", Toast.LENGTH_SHORT).show();
    }
}

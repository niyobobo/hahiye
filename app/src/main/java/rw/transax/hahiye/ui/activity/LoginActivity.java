package rw.transax.hahiye.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.Presenter;
import rw.transax.hahiye.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements Presenter {

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    public void performLogin() {

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

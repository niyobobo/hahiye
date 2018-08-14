package rw.transax.hahiye.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import rw.transax.hahiye.R;
import rw.transax.hahiye.callback.ResultCallback;
import rw.transax.hahiye.databinding.ActivityLoginBinding;
import rw.transax.hahiye.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements ResultCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        //dataBinding.setViewModel(ViewModelProviders.of(this).get(LoginViewModel.class));
        //dataBinding.executePendingBindings();
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}

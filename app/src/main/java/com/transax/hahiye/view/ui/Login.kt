package com.transax.hahiye.view.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.transax.hahiye.R
import com.transax.hahiye.databinding.ActivityLoginBinding
import com.transax.hahiye.view.callback.LoginResultCallback
import com.transax.hahiye.viewmodel.LoginViewFactory
import com.transax.hahiye.viewmodel.LoginViewModel

class Login : AppCompatActivity(), LoginResultCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        dataBinding.viewModel = ViewModelProviders
                .of(this, LoginViewFactory(this))
                .get(LoginViewModel::class.java)
        dataBinding.executePendingBindings()
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

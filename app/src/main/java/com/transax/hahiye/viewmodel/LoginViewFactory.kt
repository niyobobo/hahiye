package com.transax.hahiye.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.transax.hahiye.view.callback.LoginResultCallback

class LoginViewFactory(private val loginResultCallback: LoginResultCallback) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return LoginViewModel(loginResultCallback) as T
    }
}
package com.transax.hahiye.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.transax.hahiye.callback.ResultCallback

class LoginViewFactory(private val loginResultCallback: ResultCallback) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return LoginViewModel(loginResultCallback) as T
    }
}
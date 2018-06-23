package com.transax.hahiye.viewmodel

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.transax.hahiye.model.UserModel
import com.transax.hahiye.view.callback.LoginResultCallback

class LoginViewModel(private val callback: LoginResultCallback) : ViewModel() {

    val userModel: UserModel = UserModel()

    fun getEmailFromView(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                userModel.email = s.toString()
            }
        }
    }

    fun getPasswordFromView(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userModel.password = s.toString()
            }

        }
    }

    fun performLogin(view :View) {
        if (userModel.isValidData())
            callback.onSuccess("Information are correct")
        else
            callback.onError("Provide correct information")
    }
}
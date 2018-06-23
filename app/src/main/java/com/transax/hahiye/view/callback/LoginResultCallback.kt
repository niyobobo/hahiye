package com.transax.hahiye.view.callback

interface LoginResultCallback {
    fun onSuccess(message: String)
    fun onError(message: String)
}
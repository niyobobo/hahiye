package com.transax.hahiye.callback

interface ResultCallback {
    fun onSuccess(message: String)
    fun onError(message: String)
}
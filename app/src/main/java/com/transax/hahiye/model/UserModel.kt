package com.transax.hahiye.model

import android.text.TextUtils
import android.util.Patterns
import java.util.*

class UserModel : Observable() {
    var email: String = ""
    var password: String = ""

    fun isValidData(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(password)
    }
}

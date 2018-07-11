package com.transax.hahiye.model;

import android.text.TextUtils;
import android.util.Patterns;

public class UserModel {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidData() {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(password);
    }

}

package rw.transax.hahiye.model;

import android.arch.persistence.room.Entity;
import android.text.TextUtils;
import android.util.Patterns;

@Entity
public class UserModel {
    private String id;
    private String email;
    private String password;
    private String user_profile;


    private String getEmail() {
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

    public int isValidData() {
        if (TextUtils.isEmpty(getEmail()) && TextUtils.isEmpty(getPassword()))
            return 0;
        else if (TextUtils.isEmpty(getEmail()))
            return 1;
        else if (TextUtils.isEmpty(getPassword()))
            return 2;
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
            return 3;
        else if (getPassword().length() < 6)
            return 4;
        else
            return -1;
    }
}

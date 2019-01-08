package rw.transax.hahiye.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import rw.transax.hahiye.BR;

public class LoginModel extends BaseObservable {

    private String userEmail;
    private String userPassword;

    @Bindable
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        notifyPropertyChanged(BR.userPassword);
    }
}

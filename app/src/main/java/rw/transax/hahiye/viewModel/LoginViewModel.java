package rw.transax.hahiye.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;

import java.io.InputStream;
import java.util.Objects;

import rw.transax.hahiye.BasicApp;
import rw.transax.hahiye.R;
import rw.transax.hahiye.data.repository.DataRepository;

public class LoginViewModel extends AndroidViewModel {
    public final ObservableField<String> username = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    private static InputStream mTestStream;
    private final DataRepository dataRepository;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        mTestStream = application.getResources().openRawResource(R.raw.server);
        dataRepository = ((BasicApp) application).getDataRepository();
    }

    public void userLogin() {
        dataRepository.loginFromNetwork(username.get(), password.get(), mTestStream);
    }

    public int validatingData() {
        if (TextUtils.isEmpty(username.get()) && TextUtils.isEmpty(password.get()))
            return 0;
        else if (TextUtils.isEmpty(username.get()))
            return 1;
        else if (TextUtils.isEmpty(password.get()))
            return 2;
        else if (!Patterns.EMAIL_ADDRESS.matcher(Objects.requireNonNull(username.get())).matches())
            return 3;
        else if (Objects.requireNonNull(password.get()).length() < 6)
            return 4;
        else
            return -1;
    }
}

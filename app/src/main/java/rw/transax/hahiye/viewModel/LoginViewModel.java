package rw.transax.hahiye.viewModel;

import android.app.Application;

import java.io.InputStream;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import rw.transax.hahiye.BasicApp;
import rw.transax.hahiye.R;
import rw.transax.hahiye.data.repository.DataRepository;

public class LoginViewModel extends AndroidViewModel {
    private static InputStream mTestStream;
    private final DataRepository dataRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mTestStream = application.getResources().openRawResource(R.raw.server);
        dataRepository = ((BasicApp) application).getDataRepository();
    }

    public void userLogin(String username, String password) {
        dataRepository.loginFromNetwork(username, password, mTestStream);
    }
}

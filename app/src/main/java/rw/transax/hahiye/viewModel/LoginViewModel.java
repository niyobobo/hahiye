package rw.transax.hahiye.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rw.transax.hahiye.data.local.dao.UserDao;
import rw.transax.hahiye.data.local.database.AppDatabase;
import rw.transax.hahiye.data.remote.ApiInterface;
import rw.transax.hahiye.model.UserModel;

public class LoginViewModel extends AndroidViewModel {

    private UserDao userDao;
    private ExecutorService executorService;
    public final ObservableField<String> username = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();


    public LoginViewModel(@NonNull Application application) {
        super(application);
        //userDao = AppDatabase.getInstance(application).userDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void userLogin() {
        Call<UserModel> networkCall = ApiInterface.Factory.create().getLoginInfo(username.get(), password.get());
        networkCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(@NonNull Call<UserModel> call, @NonNull Response<UserModel> response) {
                executorService.execute(() -> userDao.insert(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<UserModel> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
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

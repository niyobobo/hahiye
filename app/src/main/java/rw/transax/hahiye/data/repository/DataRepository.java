package rw.transax.hahiye.data.repository;

import android.util.Log;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import rw.transax.hahiye.Account;
import rw.transax.hahiye.AccountRequest;
import rw.transax.hahiye.AccountServiceGrpc;
import rw.transax.hahiye.AuthRequest;
import rw.transax.hahiye.AuthResponse;
import rw.transax.hahiye.AuthServiceGrpc;
import rw.transax.hahiye.data.local.database.AppDatabase;
import rw.transax.hahiye.data.remote.RemoteData;
import rw.transax.hahiye.model.InterestModel;
import rw.transax.hahiye.model.UserModel;
import rw.transax.hahiye.utils.AppExecutors;
import rw.transax.hahiye.utils.CustomOkHttpChannelBuilder;
import rw.transax.hahiye.utils.JwtCallCredential;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DataRepository {

    private static DataRepository sInstance;
    private final AppDatabase mDatabase;
    private final AppExecutors appExecutors;
    private MediatorLiveData<List<InterestModel>> mObservableInterests;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        appExecutors = new AppExecutors();
        mObservableInterests = new MediatorLiveData<>();
        //get all interest in background
        mObservableInterests.addSource(mDatabase.interestDao().getAllInterest(),
                interestModelList -> {
                    if (mDatabase.getDatabaseCreated() != null)
                        mObservableInterests.postValue(interestModelList);
                }
        );
    }

    public static DataRepository getsInstance(AppDatabase database) {
        if (sInstance == null)
            synchronized (DataRepository.class) {
                if (sInstance == null) sInstance = new DataRepository(database);
            }
        return sInstance;
    }

    /*
     * Get the data from the database and get notified when they get changed.
     *
     * Data related to interests
     */

    public void saveAllInterests(List<InterestModel> interestModels) {
        mDatabase.interestDao().saveInterests(interestModels);
    }

    public LiveData<List<InterestModel>> getAllInterests() {
        return mObservableInterests;
    }

    public LiveData<Integer> getTotalSelectedInterests() {
        return mDatabase.interestDao().totalSelectedInterest();
    }

    public void selectInterest(InterestModel interestModel) {
        appExecutors.getDiskIO().execute(() ->
                mDatabase.interestDao().selectInterest(interestModel.isFollowed(), interestModel.getUid()));
    }

    public void addInterest(InterestModel interestModel) {
        appExecutors.getDiskIO().execute(() ->
                mDatabase.interestDao().insert(interestModel));
    }

    /**
     * User account related information
     */

    public void loginFromNetwork(String username, String password, InputStream inputStream) {
        appExecutors.getNetworkIO().execute(() -> {
            try {
                Log.d(TAG, "Connecting to secure channel....");
                ManagedChannel managedChannel = CustomOkHttpChannelBuilder.build(RemoteData.BASE_URL,
                        8080,
                        null,
                        true,
                        inputStream);
                AuthServiceGrpc.AuthServiceBlockingStub stub = AuthServiceGrpc.newBlockingStub(managedChannel);
                AuthRequest authRequest = AuthRequest.newBuilder()
                        .setUsername(username)
                        .setPassword(password)
                        .build();
                AuthResponse response = stub.login(authRequest);

                JwtCallCredential credential = new JwtCallCredential(response.getToken());
                AccountServiceGrpc.AccountServiceBlockingStub accountStub = AccountServiceGrpc
                        .newBlockingStub(managedChannel)
                        .withCallCredentials(credential);
                AccountRequest accountRequest = AccountRequest.newBuilder().build();
                Account account = accountStub.getAccount(accountRequest);

                /*
                 *  converting account response to UserModel in order to be saved into local database
                 */

                String timeStamp = String.valueOf(account.getCreatedAt());

                Date createdAt = new SimpleDateFormat().parse(timeStamp);

                UserModel userModel = new UserModel(
                        account.getId(),
                        account.getName(),
                        account.getUsername(),
                        account.getPassword(),
                        account.getEmail(),
                        account.getProfileUrl(),
                        account.getVerified(),
                        createdAt
                );
                appExecutors.getDiskIO().execute(() -> mDatabase.userDao().insert(userModel));

            } catch (StatusRuntimeException | ParseException e) {
                e.printStackTrace();
            }
        });

    }

    public LiveData<UserModel> getUserInformation() {
        return mDatabase.userDao().getUserInfo();
    }
}

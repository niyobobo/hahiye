package rw.transax.hahiye.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rw.transax.hahiye.data.local.database.AppDatabase;
import rw.transax.hahiye.model.InterestModel;

public class DataRepository {

    private static DataRepository sInstance;
    private final AppDatabase mDatabase;
    private MediatorLiveData<List<InterestModel>> mObservableInterests;
    private final ExecutorService mExecutorService;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableInterests = new MediatorLiveData<>();
        mExecutorService = Executors.newSingleThreadExecutor();
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
        mExecutorService.execute(() ->
                mDatabase.interestDao().selectInterest(interestModel.getIsFollowed(), interestModel.getUid()));
    }

    public void addInterest(InterestModel interestModel) {
        mExecutorService.execute(() ->
                mDatabase.interestDao().insert(interestModel));
    }
}

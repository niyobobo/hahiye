package rw.transax.hahiye.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rw.transax.hahiye.data.local.dao.InterestDao;
import rw.transax.hahiye.data.local.database.AppDatabase;
import rw.transax.hahiye.model.InterestModel;

public class InterestViewModel extends AndroidViewModel {

    private InterestDao interestDao;
    private ExecutorService executorService;

    public InterestViewModel(@NonNull Application application) {
        super(application);
        interestDao = AppDatabase.getDbInstance(application).interestDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void saveAllInterests(List<InterestModel> interestModels) {
        executorService.execute(() -> interestDao.saveInterests(interestModels));
    }

    public LiveData<List<InterestModel>> getAllInterests() {
        return interestDao.getAllInterest();
    }


    public void selectInterest(InterestModel interest) {
        executorService.execute(() -> interestDao.selectInterest(interest.getIsFollowed(), interest.getUid()));
    }

    public int getTotalInterest() {
        return interestDao.totalSelectedInterest();
    }

    public void addInterest(InterestModel interestModel) {
        executorService.execute(() -> interestDao.insert(interestModel));
    }
}

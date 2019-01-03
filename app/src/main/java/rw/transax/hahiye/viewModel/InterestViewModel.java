package rw.transax.hahiye.viewModel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import rw.transax.hahiye.BasicApp;
import rw.transax.hahiye.data.repository.DataRepository;
import rw.transax.hahiye.model.InterestModel;

public class InterestViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<InterestModel>> mObservableInterests;
    private final MediatorLiveData<Integer> mTotalInterest;
    private final DataRepository dataRepository;

    InterestViewModel(@NonNull Application application) {
        super(application);
        dataRepository = ((BasicApp) application).getDataRepository();
        mObservableInterests = new MediatorLiveData<>();
        mTotalInterest = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableInterests.setValue(null);
        mTotalInterest.setValue(0);
        //Get data from room database.
        LiveData<List<InterestModel>> allInterests = ((BasicApp) application).getDataRepository()
                .getAllInterests();
        LiveData<Integer> totalInterest = ((BasicApp) application).getDataRepository().getTotalSelectedInterests();
        // observe the changes of the products from the database and forward them
        mObservableInterests.addSource(allInterests, mObservableInterests::setValue);
        mTotalInterest.addSource(totalInterest, mTotalInterest::setValue);
    }

    /**
     * Exposing LiveData to the ui to be observed.
     */

    public void saveAllInterests(List<InterestModel> interestModels) {
        dataRepository.saveAllInterests(interestModels);
    }

    public LiveData<List<InterestModel>> getObservableInterests() {
        return mObservableInterests;
    }

    public void selectInterest(InterestModel interest) {
        dataRepository.selectInterest(interest);
    }

    public LiveData<Integer> getTotalInterest() {
        return mTotalInterest;
    }

    public void addInterest(InterestModel interestModel) {
        dataRepository.addInterest(interestModel);
    }

}

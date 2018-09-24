package rw.transax.hahiye;

import android.app.Application;

import rw.transax.hahiye.data.local.database.AppDatabase;
import rw.transax.hahiye.data.repository.DataRepository;
import rw.transax.hahiye.utils.AppExecutors;

public class BasicApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors = new AppExecutors();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getDataRepository() {
        return DataRepository.getsInstance(getDatabase());
    }
}

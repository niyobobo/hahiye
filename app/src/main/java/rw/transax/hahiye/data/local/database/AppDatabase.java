package rw.transax.hahiye.data.local.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import java.util.List;

import rw.transax.hahiye.data.converter.BooleanConverter;
import rw.transax.hahiye.data.converter.DateConverter;
import rw.transax.hahiye.data.local.dao.InterestDao;
import rw.transax.hahiye.data.local.dao.UserDao;
import rw.transax.hahiye.model.Interest;
import rw.transax.hahiye.model.Places;
import rw.transax.hahiye.model.User;
import rw.transax.hahiye.utils.AppExecutors;

@TypeConverters({DateConverter.class, BooleanConverter.class})
@Database(entities = {User.class, Interest.class, Places.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    @VisibleForTesting
    private static final String DATABASE_NAME = "hahiye_db";
    private static AppDatabase sInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time and Room database
     * is built on top of SQLite.
     */

    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.getDiskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            List<Interest> interests = DataGenerator.SAMPLE_DATA();
                            database.interestDao().saveInterests(interests);

                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    private static void addDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists())
            setDatabaseCreated();
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    /**
     * Note: declare abstract method for each DAO to access its member function through
     * application instance database.
     */

    public abstract UserDao userDao();

    public abstract InterestDao interestDao();
}

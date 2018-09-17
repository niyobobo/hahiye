package rw.transax.hahiye.data.local.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;

import rw.transax.hahiye.data.local.dao.InterestDao;
import rw.transax.hahiye.data.local.dao.UserDao;
import rw.transax.hahiye.model.InterestModel;
import rw.transax.hahiye.model.UserModel;

@Database(entities = {UserModel.class, InterestModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "hahiye_db";

    private static List<InterestModel> INTEREST = Arrays.asList(
            new InterestModel(UUID.randomUUID().toString(), "Hotel", "https://image.flaticon.com/icons/svg/1055/1055677.svg"),
            new InterestModel(UUID.randomUUID().toString(), "Pub", "https://image.flaticon.com/icons/svg/1055/1055677.svg"),
            new InterestModel(UUID.randomUUID().toString(), "Restaurant", "https://image.flaticon.com/icons/svg/1055/1055677.svg"),
            new InterestModel(UUID.randomUUID().toString(), "Cafe-Resto", "https://image.flaticon.com/icons/svg/1055/1055677.svg")
    );


    public static AppDatabase getDbInstance(Context context) {

        synchronized (new Object()) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadExecutor().execute(() ->
                                        getDbInstance(context).interestDao().saveInterests(INTEREST));
                            }
                        }).build();
            }
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();

    public abstract InterestDao interestDao();
}

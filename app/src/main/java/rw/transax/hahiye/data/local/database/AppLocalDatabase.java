package rw.transax.hahiye.data.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import rw.transax.hahiye.model.UserModel;

@Database(entities = {UserModel.class}, version = 1)
public abstract class AppLocalDatabase extends RoomDatabase {

    private static AppLocalDatabase mINSTANCE;

    static AppLocalDatabase getDatabase(Context context) {

        if (mINSTANCE == null) {
            mINSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppLocalDatabase.class,
                    "hahiye_db").build();
        }

        return mINSTANCE;
    }

    abstract UserModel getUserInformation();
}

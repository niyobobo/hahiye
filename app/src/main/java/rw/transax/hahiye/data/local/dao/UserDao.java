package rw.transax.hahiye.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import rw.transax.hahiye.model.UserModel;

@Dao
public interface UserDao extends GenericDao<UserModel> {

    @Query("SELECT * FROM user ")
    LiveData<UserModel> getUserInfo();
}

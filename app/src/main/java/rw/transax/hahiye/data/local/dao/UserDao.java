package rw.transax.hahiye.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import rw.transax.hahiye.model.UserModel;

@Dao
public interface UserDao extends GenericDao<UserModel> {

    @Query("SELECT * FROM user ")
    LiveData<UserModel> getUserInfo();
}

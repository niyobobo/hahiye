package rw.transax.hahiye.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import rw.transax.hahiye.model.User;

@Dao
public interface UserDao extends GenericDao<User> {

    @Query("SELECT * FROM User ")
    LiveData<User> getUserInfo();
}

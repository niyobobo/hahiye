package rw.transax.hahiye.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import rw.transax.hahiye.model.UserModel;

@Dao
public interface UserDao extends GenericDao<UserModel> {

    @Query("SELECT * FROM UserModel WHERE uid= :user_id")
    UserModel getUserInfo(String user_id);

}

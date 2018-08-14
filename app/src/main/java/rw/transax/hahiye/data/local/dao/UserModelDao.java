package rw.transax.hahiye.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import rw.transax.hahiye.model.UserModel;

@Dao
public interface UserModelDao extends GenericDao {

    @Query("SELECT * FROM UserModel WHERE id= :user_id")
    UserModel getUserInfo(String user_id);

}

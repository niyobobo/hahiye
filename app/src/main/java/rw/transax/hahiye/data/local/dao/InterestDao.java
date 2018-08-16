package rw.transax.hahiye.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import rw.transax.hahiye.model.InterestModel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface InterestDao extends GenericDao<InterestModel> {

    @Insert(onConflict = REPLACE)
    void saveAllInterest(List<InterestModel> interestModels);

    @Query("SELECT * FROM InterestModel")
    LiveData<List<InterestModel>> getAllInterest();

    @Query("SELECT * FROM InterestModel WHERE uid= :uid")
    InterestModel getInterest(String uid);
}

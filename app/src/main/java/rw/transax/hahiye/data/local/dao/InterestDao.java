package rw.transax.hahiye.data.local.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import rw.transax.hahiye.model.InterestModel;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface InterestDao extends GenericDao<InterestModel> {

    @Insert(onConflict = REPLACE)
    void saveInterests(List<InterestModel> interestModels);

    @Query("SELECT * FROM interest")
    LiveData<List<InterestModel>> getAllInterest();

    @Query("SELECT * FROM interest WHERE uid= :uid")
    InterestModel getInterest(String uid);

    @Query("UPDATE interest SET isFollowed = :value WHERE uid= :uid")
    void selectInterest(boolean value, String uid);

    @Query("SELECT COUNT(isFollowed) FROM interest WHERE isFollowed = 1")
    LiveData<Integer> totalSelectedInterest();
}

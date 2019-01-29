package rw.transax.hahiye.data.local.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import rw.transax.hahiye.model.Interest;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface InterestDao extends GenericDao<Interest> {

    @Insert(onConflict = REPLACE)
    void saveInterests(List<Interest> interests);

    @Query("SELECT * FROM Interest")
    LiveData<List<Interest>> getAllInterest();

    @Query("SELECT * FROM Interest WHERE uid= :uid")
    Interest getInterest(String uid);

    @Query("UPDATE Interest SET isFollowed = :value WHERE uid= :uid")
    void selectInterest(boolean value, String uid);

    @Query("SELECT COUNT(isFollowed) FROM Interest WHERE isFollowed = 1")
    LiveData<Integer> totalSelectedInterest();
}

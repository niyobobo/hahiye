package rw.transax.hahiye.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import rw.transax.hahiye.model.Places;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PlacesDao extends GenericDao<Places> {

    @Insert(onConflict = REPLACE)
    void insertAllPlaces(List<Places> places);

    @Query("SELECT * FROM Places")
    LiveData<List<Places>> getAllPlaces();

    @Query("SELECT * FROM Places WHERE uid= :place_uid")
    Places getPlaceInfo(String place_uid);
}

package rw.transax.hahiye.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import rw.transax.hahiye.model.PlaceModel;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PlacesDao extends GenericDao<PlaceModel> {

    @Insert(onConflict = REPLACE)
    void insertAllPlaces(List<PlaceModel> placeModels);

    @Query("SELECT * FROM places")
    LiveData<List<PlaceModel>> getAllPlaces();

    @Query("SELECT * FROM places WHERE uid= :place_uid")
    PlaceModel getPlaceInfo(String place_uid);
}

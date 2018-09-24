package rw.transax.hahiye.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import rw.transax.hahiye.model.PlaceModel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PlacesDao extends GenericDao<PlaceModel> {

    @Insert(onConflict = REPLACE)
    void insertAllPlaces(List<PlaceModel> placeModels);

    @Query("SELECT * FROM places")
    LiveData<List<PlaceModel>> getAllPlaces();

    @Query("SELECT * FROM places WHERE uid= :place_uid")
    PlaceModel getPlaceInfo(String place_uid);
}

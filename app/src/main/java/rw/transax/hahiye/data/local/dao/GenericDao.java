package rw.transax.hahiye.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * We Generalizing Dao parameter as T so that we no longer writing update, delete and insert
 * because they take same parameter (like a model object). This generalized as parameter
 * each DAO will extends this interface (means it already implemented those method).
 *
 * @param <T>
 */


@Dao
public interface GenericDao<T> {

    @Insert(onConflict = REPLACE)
    void insert(T t);

    @Update
    void update(T t);

    @Delete
    void delete(T t);
}

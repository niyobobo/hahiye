package rw.transax.hahiye.data.converter;

import android.arch.persistence.room.TypeConverter;

/**
 * Note: Room database doesn't support boolean dataType so we have to
 * convert boolean data into Integer before add data into database and convert back into
 * boolean primitive dataType when retrieving data for simplicity of code when we
 * tried to access the value of the returned object.
 */
public class BooleanConverter {

    @TypeConverter
    public static Boolean toBoolean(int a) {
        return a == 0;
    }

    @TypeConverter
    public static int toInteger(boolean selected) {
        return selected ? 1 : 0;
    }
}

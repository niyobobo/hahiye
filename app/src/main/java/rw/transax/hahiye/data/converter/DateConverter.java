package rw.transax.hahiye.data.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    /**
     * Note: Room database doesn't support Date Object type so we have to
     * convert date into Long before we add data into database and convert back into
     * Date object when retrieving data for simplicity of code when we
     * tried to access the value of the returned object.
     */
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

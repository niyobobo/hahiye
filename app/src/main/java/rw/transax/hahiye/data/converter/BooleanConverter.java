package rw.transax.hahiye.data.converter;

import android.arch.persistence.room.TypeConverter;

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

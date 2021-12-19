package com.example.data.db.converters

import androidx.room.TypeConverter
import java.util.Date

object Converters {
    @JvmStatic
    @TypeConverter
    fun Date?.convertToLong(): Long? {
        return this?.time
    }

    @JvmStatic
    @TypeConverter
    fun Long?.convertToDate(): Date? {
        return this?.let { Date(it) }
    }
}

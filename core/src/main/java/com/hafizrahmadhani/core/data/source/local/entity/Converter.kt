package com.hafizrahmadhani.core.data.source.local.entity

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class Converter {

    @TypeConverter
    fun convertGenre(value : String?) : List<String>? {
        return value?.let {
            val genre = it.split(',')
            return genre.map { list -> list }
        }
    }

    @TypeConverter
    fun convertGenreList(value: List<String>?): String? {
        return value?.joinToString { list -> list }
    }
}
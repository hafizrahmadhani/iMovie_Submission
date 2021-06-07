package com.hafizrahmadhani.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hafizrahmadhani.core.data.source.local.entity.Converter
import com.hafizrahmadhani.core.data.source.local.entity.DetailMovieEntity
import com.hafizrahmadhani.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class, DetailMovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao
}
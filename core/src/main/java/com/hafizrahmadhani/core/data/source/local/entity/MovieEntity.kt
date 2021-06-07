package com.hafizrahmadhani.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hafizrahmadhani.core.utils.TypeMovie

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    val id : Int?,
    val title : String?,
    val description : String?,
    val poster : String?,
    val vote : Double?,
    val type : Int? = TypeMovie.typeMovie,
    val status : Boolean = false,
)

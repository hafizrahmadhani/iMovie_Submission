package com.hafizrahmadhani.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hafizrahmadhani.core.utils.TypeMovie

@Entity(tableName = "detail")
data class DetailMovieEntity(
    @PrimaryKey
    val id : Int? = null,
    val title : String? = "",
    val description : String? = "",
    val poster : String? = "",
    val image : String? = "",
    val genre : List<String>,
    val date : String? = "",
    val voteAverage : Double? = null,
    val type : Int? = TypeMovie.typeMovie
)

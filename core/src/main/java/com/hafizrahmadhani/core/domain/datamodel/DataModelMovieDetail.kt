package com.hafizrahmadhani.core.domain.datamodel

import com.hafizrahmadhani.core.utils.TypeMovie

data class DataModelMovieDetail(
    val id : Int? = null,
    val title : String? = "",
    val description : String? = "",
    val poster : String? = "",
    val image : String? = "",
    val genre : List<DataModelGenre> = listOf(),
    val date : String? = "",
    val voteAverage : Double? = null,
    val type : Int? = TypeMovie.typeMovie
)

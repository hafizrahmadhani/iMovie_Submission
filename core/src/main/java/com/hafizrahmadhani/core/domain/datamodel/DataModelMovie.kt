package com.hafizrahmadhani.core.domain.datamodel

import com.hafizrahmadhani.core.utils.TypeMovie

data class DataModelMovie(
    val id : Int?,
    val title : String?,
    val description : String?,
    val poster : String?,
    val vote : Double?,
    val type : Int? = TypeMovie.typeMovie,
    val status : Boolean = false
)

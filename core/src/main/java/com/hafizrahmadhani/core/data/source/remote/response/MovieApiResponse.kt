package com.hafizrahmadhani.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieApiResponse(
    @SerializedName("id")
    val id : Int,

    @SerializedName("title")
    val title : String,

    @SerializedName("overview")
    val overview : String,

    @SerializedName("poster_path")
    val poster_path : String,

    @SerializedName("vote_average")
    val vote_average : Double
)

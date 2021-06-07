package com.hafizrahmadhani.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title", alternate = ["name"])
    val title: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("poster_path")
    val poster_path: String? = null,

    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,

    @SerializedName("genres")
    val genres: List<GenreResponse>,

    @SerializedName("release_date", alternate = ["first_air_date"])
    val release_date: String? = null,

    @SerializedName("vote_average")
    val vote_average : Double? = null
)

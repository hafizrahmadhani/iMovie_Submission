package com.hafizrahmadhani.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val movie : List<MovieApiResponse>
)

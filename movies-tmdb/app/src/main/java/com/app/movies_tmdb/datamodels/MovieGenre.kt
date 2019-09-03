package com.app.movies_tmdb.datamodels

import com.google.gson.annotations.SerializedName

/**
 * single movie genre
 */
data class MovieGenre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

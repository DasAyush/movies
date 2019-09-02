package com.app.movies_tmdb.datamodels

import com.google.gson.annotations.SerializedName

data class DateModel(

    @SerializedName("maximum") var maximum: String,
    @SerializedName("minimum") var minimum: String

)
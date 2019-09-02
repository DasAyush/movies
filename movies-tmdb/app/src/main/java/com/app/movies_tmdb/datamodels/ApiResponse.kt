package com.app.movies_tmdb.datamodels

import com.google.gson.annotations.SerializedName

class ApiResponse<T>(

    @SerializedName("page") var page: Int,
    @SerializedName("total_results") var totalResults: Int,
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("results") var results: List<Movies>,
    @SerializedName("dates") var dates: DateModel

)
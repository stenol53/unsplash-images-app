package com.voak.android.unsplashimages.models

import com.google.gson.annotations.SerializedName

data class UnsplashResponse(
    @SerializedName("results")
    val results: List<UnsplashPhoto>
)
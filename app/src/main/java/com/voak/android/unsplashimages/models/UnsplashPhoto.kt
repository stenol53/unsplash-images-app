package com.voak.android.unsplashimages.models

import com.google.gson.annotations.SerializedName

data class UnsplashPhoto(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("urls")
    val urls: UnsplashPhotoUrls,
    @SerializedName("user")
    val user: UnsplashUser
) {
    data class UnsplashPhotoUrls(
        @SerializedName("raw")
        val raw: String,
        @SerializedName("full")
        val full: String,
        @SerializedName("regular")
        val regular: String,
        @SerializedName("small")
        val small: String,
        @SerializedName("thumb")
        val thumb: String
    )

    data class UnsplashUser(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("username")
        val userName: String
    )
}
package com.voak.android.unsplashimages.api

import com.voak.android.unsplashimages.BuildConfig
import com.voak.android.unsplashimages.models.UnsplashResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @GET("search/photos")
    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : Single<UnsplashResponse>


    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    }
}
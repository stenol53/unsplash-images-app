package com.voak.android.unsplashimages.repository

import androidx.paging.rxjava2.RxPagingSource
import com.voak.android.unsplashimages.api.UnsplashApi
import com.voak.android.unsplashimages.models.UnsplashPhoto
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

private const val UNSPLASH_STARTING_PAGE = 1

class UnsplashPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String
) : RxPagingSource<Int, UnsplashPhoto>() {
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, UnsplashPhoto>> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE

        return unsplashApi.searchPhotos(query, page, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map { response ->
                val photos = response.results
                LoadResult.Page(
                    data = photos,
                    prevKey = if (page == UNSPLASH_STARTING_PAGE) null else page - 1,
                    nextKey = if (photos.isEmpty()) null else page + 1
                ) as LoadResult<Int, UnsplashPhoto>
            }
            .onErrorReturn { error -> LoadResult.Error(error) }
    }
}
package com.voak.android.unsplashimages.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.voak.android.unsplashimages.base.BaseApp
import com.voak.android.unsplashimages.models.UnsplashPhoto
import com.voak.android.unsplashimages.repository.UnsplashRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GalleryViewModel : ViewModel() {

    @Inject lateinit var repository: UnsplashRepository

    init {
        BaseApp.instance.component?.inject(this)
    }

    var photos = repository.getSearchResult(DEFAULT_QUERY).cachedIn(viewModelScope)

    fun getSearchResult(query: String): Flowable<PagingData<UnsplashPhoto>> {
        photos = repository.getSearchResult(query).cachedIn(viewModelScope)
        return photos
    }

    companion object {
        private const val DEFAULT_QUERY = "cats"
    }
}
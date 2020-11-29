package com.voak.android.unsplashimages.di.components

import com.voak.android.unsplashimages.di.modules.AppModule
import com.voak.android.unsplashimages.di.modules.NetworkModule
import com.voak.android.unsplashimages.ui.viewModels.GalleryViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(viewModel: GalleryViewModel)
}
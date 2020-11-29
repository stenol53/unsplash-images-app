package com.voak.android.unsplashimages.di.modules

import android.content.Context
import com.voak.android.unsplashimages.base.BaseApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: BaseApp) {

    @Singleton
    @Provides
    fun provideContext(): Context = app.applicationContext
}
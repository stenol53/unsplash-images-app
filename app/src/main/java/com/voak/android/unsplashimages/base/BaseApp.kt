package com.voak.android.unsplashimages.base

import android.app.Application
import com.voak.android.unsplashimages.di.components.AppComponent
import com.voak.android.unsplashimages.di.components.DaggerAppComponent
import com.voak.android.unsplashimages.di.modules.AppModule

class BaseApp : Application() {

    var component: AppComponent? = null
        get() {
            if (field == null) {
                field = DaggerAppComponent
                    .builder()
                    .appModule(AppModule(this))
                    .build()
            }
            return field
        }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        lateinit var instance: BaseApp
    }
}
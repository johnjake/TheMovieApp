package com.themovieguide.org.di.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.themovieguide.org.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TheMovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        ViewModelProvider.AndroidViewModelFactory.getInstance(this)
    }
}

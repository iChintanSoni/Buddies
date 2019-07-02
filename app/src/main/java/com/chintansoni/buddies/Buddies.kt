package com.chintansoni.buddies

import android.app.Application
import com.chintansoni.buddies.di.databaseModule
import com.chintansoni.buddies.di.networkModule
import com.chintansoni.buddies.di.viewModelModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class Buddies : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeTimber()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin(this, listOf(networkModule, databaseModule, viewModelModule))
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
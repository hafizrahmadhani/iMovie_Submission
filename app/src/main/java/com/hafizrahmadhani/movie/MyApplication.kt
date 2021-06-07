@file:Suppress("unused")

package com.hafizrahmadhani.movie

import android.app.Application
import com.hafizrahmadhani.core.di.coreModule
import com.hafizrahmadhani.core.di.databaseModule
import com.hafizrahmadhani.core.di.networkModule
import com.hafizrahmadhani.movie.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    coreModule,
                    databaseModule,
                    networkModule,
                    appModule
                )
            )
        }
    }
}
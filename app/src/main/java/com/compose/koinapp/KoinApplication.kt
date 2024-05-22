package com.compose.koinapp

import android.app.Application
import com.compose.koinapp.di.networkModule
import com.compose.koinapp.di.remoteDataSourceModule
import com.compose.koinapp.di.repositoryModule
import com.compose.koinapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinApplication)
            androidLogger()
            modules(networkModule, remoteDataSourceModule, repositoryModule, viewModelModule)
        }
    }


}
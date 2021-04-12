package com.feiyatsu.rickmortyapplication

import android.app.Application
import com.feiyatsu.rickmortyapplication.di.rickAppModule
import com.feiyatsu.rickmortyapplication.di.viewModelModule
import org.koin.core.context.startKoin

class RickApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(rickAppModule, viewModelModule)
        }
    }
}
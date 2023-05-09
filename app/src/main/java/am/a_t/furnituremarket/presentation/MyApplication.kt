package am.a_t.furnituremarket.presentation

import am.a_t.furnituremarket.di.preferencesModule
import am.a_t.furnituremarket.di.repositoryModule
import am.a_t.furnituremarket.di.viewModelModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(preferencesModule, repositoryModule, viewModelModule)
        }
    }

}
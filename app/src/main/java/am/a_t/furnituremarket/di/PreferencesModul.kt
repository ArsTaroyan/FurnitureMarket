package am.a_t.furnituremarket.di

import am.a_t.furnituremarket.data.preferences.Preference
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module {
    single {
        Preference(androidContext())
    }
}
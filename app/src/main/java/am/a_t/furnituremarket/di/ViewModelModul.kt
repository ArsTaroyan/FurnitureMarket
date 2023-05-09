package am.a_t.furnituremarket.di

import am.a_t.furnituremarket.presentation.viewModel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ActivityViewModel(get()) }

    viewModel { LogInViewModel(get()) }

    viewModel { ProfileViewModel(get()) }

    viewModel { SaveViewModel() }

    viewModel { ShopViewModel() }

    viewModel { SignUpViewModel(get()) }

    viewModel { SplashViewModel(get()) }

    viewModel { HomeViewModel() }

    viewModel { OpenViewModel(get()) }
}
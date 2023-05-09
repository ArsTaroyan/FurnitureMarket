package am.a_t.furnituremarket.presentation.viewModel

import am.a_t.furnituremarket.data.repo.Repository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: Repository) : ViewModel() {
    val authLiveData = MutableSharedFlow<Boolean>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            authLiveData.emit(repository.auth())
        }
    }
}
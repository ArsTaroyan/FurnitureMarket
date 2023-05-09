package am.a_t.furnituremarket.presentation.viewModel

import am.a_t.furnituremarket.data.repo.Repository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: Repository) : ViewModel() {
    val logOutLiveData = MutableSharedFlow<Boolean>()

    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
            logOutLiveData.emit(repository.removeUser())
        }
    }
}
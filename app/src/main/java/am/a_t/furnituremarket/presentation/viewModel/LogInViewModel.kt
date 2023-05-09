package am.a_t.furnituremarket.presentation.viewModel

import am.a_t.furnituremarket.data.repo.Repository
import am.a_t.furnituremarket.domain.entity.User
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class LogInViewModel(private val repository: Repository) : ViewModel() {
    val getUserLiveData = MutableSharedFlow<User?>()

    fun getUser(name: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getUserLiveData.emit(repository.getUser(name, password))
        }
    }
}
package am.a_t.furnituremarket.presentation.viewModel

import am.a_t.furnituremarket.data.repo.Repository
import am.a_t.furnituremarket.domain.entity.User
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: Repository) : ViewModel() {
    val addUserLiveData = MutableSharedFlow<Boolean>()

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            addUserLiveData.emit(repository.addUser(user))
        }
    }
}
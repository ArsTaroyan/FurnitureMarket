package am.a_t.furnituremarket.presentation.viewModel

import am.a_t.furnituremarket.data.repo.Repository
import am.a_t.furnituremarket.domain.entity.Product
import am.a_t.furnituremarket.domain.entity.User
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ActivityViewModel(private val repository: Repository) : ViewModel() {
    val localLiveData = MutableSharedFlow<User?>(1)
    val getProductLiveData = MutableSharedFlow<Product?>()
    val getAllProductLiveData = MutableSharedFlow<Flow<List<Product>>>(1)

    private val _getAllProduct: MutableStateFlow<List<Product>?> = MutableStateFlow(null)

    fun getAllProduct(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _getAllProduct.value = repository.getAllProduct(id).first()
            getAllProductLiveData.emit(repository.getAllProduct(id))
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            localLiveData.emit(repository.getLocal())
        }
    }

    fun getProduct(isClick: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            getProductLiveData.emit(repository.getProduct(isClick))
        }
    }

    fun updateUserProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserProduct(product)
        }
    }
}
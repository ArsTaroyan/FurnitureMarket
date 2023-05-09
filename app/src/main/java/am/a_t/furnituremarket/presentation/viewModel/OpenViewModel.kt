package am.a_t.furnituremarket.presentation.viewModel

import am.a_t.furnituremarket.data.repo.Repository
import am.a_t.furnituremarket.domain.entity.Product
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OpenViewModel(private val repository: Repository) : ViewModel() {

    fun insertProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProduct(product)
        }
    }

}
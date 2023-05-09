package am.a_t.furnituremarket.presentation.viewModel

import am.a_t.furnituremarket.domain.entity.Product
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

class HomeViewModel : ViewModel() {
    private val _filter: MutableStateFlow<List<Product?>?> = MutableStateFlow(null)
    val filter = _filter.asSharedFlow()

    fun getFilterProduct(products: List<Product>, type: String) {
        val newList = mutableListOf<Product?>()
        for (i in products.indices) {
            if (products[i].type == type) {
                newList.add(products[i])
            }
        }
        _filter.value = newList
    }
}
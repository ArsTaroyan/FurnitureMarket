package am.a_t.furnituremarket.presentation.viewModel

import am.a_t.furnituremarket.domain.entity.Product
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

class SaveViewModel : ViewModel() {
    private val _save: MutableStateFlow<List<Product?>?> = MutableStateFlow(null)
    val save = _save.asSharedFlow()

    fun getSaveProduct(products: List<Product>) {
        val newList = mutableListOf<Product?>()
        for (i in products.indices) {
            if (products[i].productIsSave) {
                newList.add(products[i])
            }
        }
        _save.value = newList
    }
}
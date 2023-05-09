package am.a_t.furnituremarket.presentation.viewModel

import am.a_t.furnituremarket.domain.entity.Product
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

class ShopViewModel : ViewModel() {
    private val _shop: MutableStateFlow<List<Product?>?> = MutableStateFlow(null)
    val shop = _shop.asSharedFlow()

    fun getShopProduct(products: List<Product>) {
        val newList = mutableListOf<Product?>()
        for (i in products.indices) {
            if (products[i].productIsShop) {
                newList.add(products[i])
            }
        }
        _shop.value = newList
    }
}
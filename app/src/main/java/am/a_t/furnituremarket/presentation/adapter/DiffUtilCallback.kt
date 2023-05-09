package am.a_t.furnituremarket.presentation.adapter

import am.a_t.furnituremarket.domain.entity.Product
import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.productId == newItem.productId

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
}
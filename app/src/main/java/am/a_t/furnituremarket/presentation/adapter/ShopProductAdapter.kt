package am.a_t.furnituremarket.presentation.adapter

import am.a_t.furnituremarket.R
import am.a_t.furnituremarket.databinding.ItemShopProductBinding
import am.a_t.furnituremarket.domain.entity.Product
import am.a_t.furnituremarket.presentation.viewModel.ActivityViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ShopProductAdapter(
    private val viewModel: ActivityViewModel,
    private val click: (Product) -> Unit
) :
    ListAdapter<Product, ShopProductAdapter.ShopProductHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopProductHolder {
        return ShopProductHolder(
            ItemShopProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShopProductHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ShopProductHolder(val binding: ItemShopProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            with(binding) {
                root.setOnClickListener {
                    viewModel.updateUserProduct(product.copy(isClick = true))
                    click(product)
                }

                Glide
                    .with(imgProductItem)
                    .load(product.productImg)
                    .centerCrop()
                    .into(imgProductItem)

                btnRemoveItem.setOnClickListener {
                    viewModel.updateUserProduct(product.copy(productIsShop = false, productCount = 1))
                }

                btnAdd.setOnClickListener {
                    viewModel.updateUserProduct(product.copy(productCount = ++product.productCount))
                    tvCount.text = product.productCount.toString()
                    tvPriceItem.text = String.format(itemView.context.getString(R.string.price_), (product.productPrice.toInt() * product.productCount))
                }

                btnRemove.setOnClickListener {
                    if (tvCount.text.toString() != "1") {
                        viewModel.updateUserProduct(product.copy(productCount = --product.productCount))
                        tvCount.text = product.productCount.toString()
                        tvPriceItem.text = String.format(itemView.context.getString(R.string.price_), (product.productPrice.toInt() * product.productCount))
                    }
                }

                tvNameItem.text = product.productName
                tvTypeItem.text = product.productType
                tvPriceItem.text = String.format(itemView.context.getString(R.string.price_), (product.productPrice.toInt() * product.productCount))
                tvStarCountItem.text = product.productRating
                tvCount.text = product.productCount.toString()
            }
        }
    }
}
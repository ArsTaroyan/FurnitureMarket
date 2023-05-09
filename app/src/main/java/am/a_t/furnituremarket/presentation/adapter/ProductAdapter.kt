package am.a_t.furnituremarket.presentation.adapter

import am.a_t.furnituremarket.R
import am.a_t.furnituremarket.databinding.ItemProductBinding
import am.a_t.furnituremarket.domain.entity.Product
import am.a_t.furnituremarket.presentation.viewModel.ActivityViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(
    private val viewModel: ActivityViewModel,
    private val click: (Product) -> Unit) :
    ListAdapter<Product, ProductAdapter.ProductHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductHolder(val binding: ItemProductBinding) :
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

                if (product.productIsSave) {
                    btnSaveItem.setBackgroundResource(R.drawable.ic_save)
                } else {
                    btnSaveItem.setBackgroundResource(R.drawable.ic_save_is)
                }

                btnSaveItem.setOnClickListener {
                    if (product.productIsSave) {
                        viewModel.updateUserProduct(product.copy(productIsSave = false))
                        btnSaveItem.setBackgroundResource(R.drawable.ic_save_is)
                    } else {
                        viewModel.updateUserProduct(product.copy(productIsSave = true))
                        btnSaveItem.setBackgroundResource(R.drawable.ic_save)
                    }
                }

                btnShopItem.setOnClickListener {
                    if (product.productIsShop) {
                        viewModel.updateUserProduct(product.copy(productIsShop = false))
                    } else {
                        viewModel.updateUserProduct(product.copy(productIsShop = true))
                    }
                }

                tvNameItem.text = product.productName
                tvTypeItem.text = product.productType
                tvPriceItem.text =
                    String.format(itemView.context.getString(R.string.price_), product.productPrice)
                tvStarCountItem.text = product.productRating

            }
        }
    }
}
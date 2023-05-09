package am.a_t.furnituremarket.presentation.ui

import am.a_t.furnituremarket.R
import am.a_t.furnituremarket.databinding.FragmentDetailsBinding
import am.a_t.furnituremarket.domain.entity.Product
import am.a_t.furnituremarket.presentation.viewModel.ActivityViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModel<ActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        initViewModel()

        return binding.root
    }

    private fun initViewModel() {
        viewModel.getProduct(true)

        lifecycleScope.launch {
            viewModel.getProductLiveData.collectLatest { product ->
                if (product != null) {
                    initClickListeners(product)
                    setView(product)
                }
            }
        }
    }

    private fun setView(product: Product) {
        with(binding) {
            Glide
                .with(imgProductDetails)
                .load(product.productImg)
                .centerCrop()
                .into(imgProductDetails)

            if (product.productIsShop) {
                btnShop.setText(R.string.thank_you)
            }

            if (product.productIsSave) {
                btnSave.setBackgroundResource(R.drawable.ic_save)
            }

            tvProductName.text = product.productName
            tvChairTypeDetails.text = product.productType
            tvPriceDetails.text =
                String.format(requireContext().getString(R.string.price_), product.productPrice)
            tvColorDetails.text = product.productColor
            tvSizeDetails.text = product.productSize
        }
    }

    private fun initClickListeners(product: Product) {
        with(binding) {
            btnSave.setOnClickListener {
                if (product.productIsSave) {
                    product.productIsSave = false
                    viewModel.updateUserProduct(product.copy(productIsSave = false))
                    btnSave.setBackgroundResource(R.drawable.ic_save_is)
                } else {
                    product.productIsSave = true
                    viewModel.updateUserProduct(product.copy(productIsSave = true))
                    btnSave.setBackgroundResource(R.drawable.ic_save)
                }
            }

            btnShop.setOnClickListener {
                viewModel.updateUserProduct(product.copy(productIsShop = true))
                btnShop.setText(R.string.thank_you)
            }
        }
    }
}
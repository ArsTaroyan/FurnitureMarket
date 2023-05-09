package am.a_t.furnituremarket.presentation.ui

import am.a_t.furnituremarket.databinding.FragmentShopBinding
import am.a_t.furnituremarket.presentation.MainActivity
import am.a_t.furnituremarket.presentation.adapter.ShopProductAdapter
import am.a_t.furnituremarket.presentation.viewModel.ActivityViewModel
import am.a_t.furnituremarket.presentation.viewModel.ShopViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopFragment : Fragment() {
    private lateinit var binding: FragmentShopBinding
    private lateinit var productAdapter: ShopProductAdapter
    private val viewModel by viewModel<ShopViewModel>()
    private val activityViewModel by viewModel<ActivityViewModel>()
    private var id: Long = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopBinding.inflate(inflater, container, false)

        initViewModel()

        return binding.root
    }

    private fun initViewModel() {
        activityViewModel.getProduct(true)

        lifecycleScope.launch {
            activityViewModel.getProductLiveData.collect {
                if (it != null) {
                    activityViewModel.updateUserProduct(it.copy(isClick = false))
                }
            }
        }

        lifecycleScope.launch {
            activityViewModel.localLiveData.collectLatest { user ->
                if (user != null) {
                    id = user.userId
                    productAdapter = ShopProductAdapter(activityViewModel) {
                        (context as MainActivity).openFragment(DetailsFragment(), true)
                    }
                    binding.rvShop.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvShop.adapter = productAdapter
                }
            }
        }

        lifecycleScope.launch {
            delay(100)
            activityViewModel.getAllProduct(id)
            activityViewModel.getAllProductLiveData.first().collectLatest {
                viewModel.getShopProduct(it)
            }
        }

        lifecycleScope.launch {
            delay(150)
            viewModel.shop.collectLatest {
                productAdapter.submitList(it)
            }
        }
    }

}
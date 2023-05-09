package am.a_t.furnituremarket.presentation.ui

import am.a_t.furnituremarket.databinding.FragmentSaveBinding
import am.a_t.furnituremarket.presentation.MainActivity
import am.a_t.furnituremarket.presentation.adapter.ProductAdapter
import am.a_t.furnituremarket.presentation.viewModel.ActivityViewModel
import am.a_t.furnituremarket.presentation.viewModel.SaveViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaveFragment : Fragment() {
    private lateinit var binding: FragmentSaveBinding
    private lateinit var productAdapter: ProductAdapter
    private val viewModel by viewModel<SaveViewModel>()
    private val activityViewModel by viewModel<ActivityViewModel>()
    private var id: Long = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveBinding.inflate(inflater, container, false)

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
            delay(100)
            activityViewModel.localLiveData.collectLatest { user ->
                if (user != null) {
                    id = user.userId
                    productAdapter = ProductAdapter(activityViewModel) {
                        (context as MainActivity).openFragment(DetailsFragment(), true)
                    }
                    binding.rvFavorite.adapter = productAdapter
                }
            }
        }

        lifecycleScope.launch {
            delay(150)
            activityViewModel.getAllProduct(id)
            activityViewModel.getAllProductLiveData.first().collectLatest {
                viewModel.getSaveProduct(it)
            }
        }

        lifecycleScope.launch {
            delay(200)
            viewModel.save.collectLatest {
                productAdapter.submitList(it)
            }
        }
    }
}
package am.a_t.furnituremarket.presentation.ui

import am.a_t.furnituremarket.R
import am.a_t.furnituremarket.databinding.FragmentHomeBinding
import am.a_t.furnituremarket.domain.entity.Product
import am.a_t.furnituremarket.presentation.MainActivity
import am.a_t.furnituremarket.presentation.adapter.ProductAdapter
import am.a_t.furnituremarket.presentation.ui.OpenFragment.Companion.BEDS
import am.a_t.furnituremarket.presentation.ui.OpenFragment.Companion.CHAIRS
import am.a_t.furnituremarket.presentation.ui.OpenFragment.Companion.SOFA
import am.a_t.furnituremarket.presentation.ui.OpenFragment.Companion.TABLES
import am.a_t.furnituremarket.presentation.viewModel.ActivityViewModel
import am.a_t.furnituremarket.presentation.viewModel.HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var productAdapter: ProductAdapter
    private val activityViewModel by viewModel<ActivityViewModel>()
    private val viewModel by viewModel<HomeViewModel>()
    private var productList = listOf<Product>()
    private var filterList: List<Product?>? = null
    private var id: Long = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initViewModel()
        initClickListeners()

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
                    binding.rvProduct.adapter = productAdapter
                }
            }
        }

        lifecycleScope.launch {
            delay(150)
            activityViewModel.getAllProduct(id)

            activityViewModel.getAllProductLiveData.first().collectLatest {
                if (productList.isEmpty()) {
                    productList = it
                    setClickDesignerOn(binding.btnChairs, CHAIRS)
                } else {
                    productList = it
                    when (filterList?.get(0)?.type) {
                        CHAIRS -> {
                            setClickDesignerOn(binding.btnChairs, CHAIRS)
                        }
                        SOFA -> {
                            setClickDesignerOn(binding.btnSofa, SOFA)
                        }
                        TABLES -> {
                            setClickDesignerOn(binding.btnTables, TABLES)
                        }
                        BEDS -> {
                            setClickDesignerOn(binding.btnBeds, BEDS)
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            delay(200)
            viewModel.filter.collectLatest {
                filterList = it
                productAdapter.submitList(it)
            }
        }
    }

    private fun initClickListeners() {
        with(binding) {
            btnChairs.setOnClickListener {
                setClickDesignerOn(btnChairs, CHAIRS)
            }

            btnSofa.setOnClickListener {
                setClickDesignerOn(btnSofa, SOFA)
            }

            btnTables.setOnClickListener {
                setClickDesignerOn(btnTables, TABLES)
            }

            btnBeds.setOnClickListener {
                setClickDesignerOn(btnBeds, BEDS)
            }
        }
    }

    private fun setClickDesignerOn(btn: AppCompatTextView, type: String) {
            btn.setBackgroundResource(R.drawable.click_back)
            btn.setTextColor(resources.getColor(R.color.white, null))
            viewModel.getFilterProduct(productList, type)

        with(binding) {
            if (btn != btnChairs) {
                setClickDesignerOff(btnChairs)
            }
            if (btn != btnSofa) {
                setClickDesignerOff(btnSofa)
            }
            if (btn != btnTables) {
                setClickDesignerOff(btnTables)
            }
            if (btn != btnBeds) {
                setClickDesignerOff(btnBeds)
            }
        }

    }

    private fun setClickDesignerOff(btn: AppCompatTextView) {
        btn.setBackgroundResource(R.drawable.btn_category)
        btn.setTextColor(resources.getColor(R.color.black, null))
    }

}
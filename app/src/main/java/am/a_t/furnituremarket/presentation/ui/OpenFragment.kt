package am.a_t.furnituremarket.presentation.ui

import am.a_t.furnituremarket.R
import am.a_t.furnituremarket.databinding.FragmentOpenBinding
import am.a_t.furnituremarket.domain.entity.Product
import am.a_t.furnituremarket.presentation.viewModel.ActivityViewModel
import am.a_t.furnituremarket.presentation.viewModel.OpenViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OpenFragment : Fragment() {
    private lateinit var binding: FragmentOpenBinding
    private lateinit var productChairs: Product
    private lateinit var productSofa: Product
    private lateinit var productTables: Product
    private lateinit var productBeds: Product
    private val activityViewModel by viewModel<ActivityViewModel>()
    private val viewModel by viewModel<OpenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOpenBinding.inflate(inflater, container, false)

        initViewModel()
        initClickListeners()

        return binding.root
    }

    private fun setList(id: Long) {
        productChairs = Product(
            0,
            0,
            "Chair",
            "https://www.pngplay.com/wp-content/uploads/2/Modern-Chair-PNG-HD-Quality.png",
            "Lirving Room",
            "430",
            1,
            "Grey",
            "30",
            "4.3",
            isClick = false,
            productIsSave = false,
            productIsShop = false,
            type = CHAIRS
        )

        productSofa = Product(
            0,
            0,
            "Sofa",
            "https://pngimg.com/uploads/sofa/small/sofa_PNG6912.png",
            "Room",
            "640",
            1,
            "Grey",
            "55",
            "4.8",
            isClick = false,
            productIsSave = false,
            productIsShop = false,
            type = SOFA
        )

        productTables = Product(
            0,
            0,
            "Table",
            "https://www.pngall.com/wp-content/uploads/11/Wooden-Furniture-Table-PNG-Pic.png",
            "Room",
            "300",
            1,
            "Grey",
            "40",
            "4.5",
            isClick = false,
            productIsSave = false,
            productIsShop = false,
            type = TABLES
        )

        productBeds = Product(
            0,
            0,
            "Bed",
            "https://pngimg.com/d/bed_PNG17418.png",
            "Room",
            "750",
            1,
            "Grey",
            "80",
            "4.7",
            isClick = false,
            productIsSave = false,
            productIsShop = false,
            type = BEDS
        )

        for (i in 0..10) {
            viewModel.insertProduct(productChairs.copy(user_id = id))
            viewModel.insertProduct(productSofa.copy(user_id = id))
            viewModel.insertProduct(productTables.copy(user_id = id))
            viewModel.insertProduct(productBeds.copy(user_id = id))
        }

    }

    private fun initViewModel() {
        lifecycleScope.launch {
            activityViewModel.localLiveData.collect {
                if (it != null) {
                    setList(it.userId)
                }
            }
        }
    }

    private fun initClickListeners() {
        binding.btnOpen.setOnClickListener {
            findNavController().navigate(R.id.action_openFragment_to_parentFragment)
        }
    }

    companion object {
        const val CHAIRS: String = "chairs"
        const val SOFA: String = "sofa"
        const val TABLES: String = "tables"
        const val BEDS: String = "beds"
    }

}
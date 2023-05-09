package am.a_t.furnituremarket.presentation.ui

import am.a_t.furnituremarket.R
import am.a_t.furnituremarket.databinding.FragmentParentBinding
import am.a_t.furnituremarket.presentation.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ParentFragment : Fragment() {
    private lateinit var binding: FragmentParentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentParentBinding.inflate(inflater, container, false)

        openFirstFragment()
        initBottomNavigationView()

        return binding.root
    }

    private fun openFirstFragment() {
        (context as MainActivity).openFragment(HomeFragment(), false)
    }

    private fun initBottomNavigationView() {
        binding.btnNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.idHome -> {
                    (context as MainActivity).openFragment(HomeFragment(), false)
                }
                R.id.idShop -> {
                    (context as MainActivity).openFragment(ShopFragment(), false)
                }
                R.id.idSave -> {
                    (context as MainActivity).openFragment(SaveFragment(), false)
                }
                R.id.idProfile -> {
                    (context as MainActivity).openFragment(ProfileFragment(), false)
                }
            }
            true
        }
    }
}
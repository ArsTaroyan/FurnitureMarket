package am.a_t.furnituremarket.presentation.ui

import am.a_t.furnituremarket.R
import am.a_t.furnituremarket.databinding.FragmentProfileBinding
import am.a_t.furnituremarket.extension.toast
import am.a_t.furnituremarket.presentation.viewModel.ActivityViewModel
import am.a_t.furnituremarket.presentation.viewModel.ProfileViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModel<ProfileViewModel>()
    private val activityViewModel by viewModel<ActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        initViewModel()
        iniClickListeners()

        return binding.root
    }

    private fun iniClickListeners() {
        binding.btnLogOut.setOnClickListener {
            viewModel.logOut()
        }
    }

    private fun initViewModel() {
        activityViewModel.getProduct(true)

        lifecycleScope.launch {
            activityViewModel.getProductLiveData.collect {
                if (it != null) {
                    it.isClick = false
                    activityViewModel.updateUserProduct(it.copy(isClick = false))
                }
            }
        }

        lifecycleScope.launch {
            viewModel.logOutLiveData.collect {
                if (it) {
                    findNavController().navigate(R.id.action_parentFragment_to_signUpFragment)
                } else {
                    requireContext().toast("You can't Log Out in this account")
                }
            }
        }

        lifecycleScope.launch {
            activityViewModel.localLiveData.collect {
                binding.tvUserName.text = it?.userName
            }
        }
    }

}
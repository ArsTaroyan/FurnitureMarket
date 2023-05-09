package am.a_t.furnituremarket.presentation.ui

import am.a_t.furnituremarket.R
import am.a_t.furnituremarket.databinding.FragmentSplashBinding
import am.a_t.furnituremarket.presentation.viewModel.SplashViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        initViewModel()

        return binding.root
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            viewModel.authLiveData.collect {
                delay(3000)
                if (it) {
                    findNavController().navigate(R.id.action_splashFragment_to_parentFragment)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_signUpFragment)
                }
            }
        }
    }

}
package am.a_t.furnituremarket.presentation.ui

import am.a_t.furnituremarket.R
import am.a_t.furnituremarket.databinding.FragmentLogInBinding
import am.a_t.furnituremarket.extension.spannableText
import am.a_t.furnituremarket.extension.toast
import am.a_t.furnituremarket.presentation.viewModel.LogInViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private val viewModel by viewModel<LogInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)

        setView()
        initClickListeners()
        initViewModel()

        return binding.root
    }

    private fun initClickListeners() {
        with(binding) {
            btnSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
            }
            btnLogIn.setOnClickListener {
                if (edName.text.toString().isEmpty() || edPassword.text.toString().isEmpty()) {
                    requireContext().toast("Please enter username or password")
                } else {
                    viewModel.getUser(
                        edName.text.toString(),
                        edPassword.text.toString()
                    )
                }
            }
        }
    }

    private fun setView() {
        with(binding) {
            tvLogoName.spannableText(
                9,
                tvLogoName.text.length,
                resources.getColor(R.color.black, null)
            )
            btnSignUp.spannableText(
                22,
                btnSignUp.text.length,
                resources.getColor(R.color.main, null)
            )
        }
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            viewModel.getUserLiveData.collect {
                if (it != null) {
                    findNavController().navigate(R.id.action_logInFragment_to_parentFragment)
                } else {
                    requireContext().toast("Username or password incorrect")
                    binding.edPassword.setText("")
                }
            }
        }
    }

}
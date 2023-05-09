package am.a_t.furnituremarket.presentation.ui

import am.a_t.furnituremarket.R
import am.a_t.furnituremarket.databinding.FragmentSignUpBinding
import am.a_t.furnituremarket.domain.entity.User
import am.a_t.furnituremarket.extension.spannableText
import am.a_t.furnituremarket.extension.toast
import am.a_t.furnituremarket.presentation.viewModel.SignUpViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel by viewModel<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        setView()
        initClickListeners()
        initViewModel()

        return binding.root
    }

    private fun initClickListeners() {
        with(binding) {
            btnSignUp.setOnClickListener {
                if (edName.text.toString().isEmpty() || edPassword.text.toString().isEmpty()) {
                    requireContext().toast("Please enter username or password")
                } else if (edPassword.text.toString() != edPasswordAgain.text.toString()) {
                    requireContext().toast("Repeated password incorrect or not entered")
                    edPassword.setText("")
                    edPasswordAgain.setText("")
                } else if (edPassword.text.toString().length < 8) {
                    requireContext().toast("Password min size can't < 8")
                } else {
                    viewModel.addUser(
                        User(
                            0,
                            edName.text.toString(),
                            edPassword.text.toString(),
                            ArrayList()
                        )
                    )
                }
            }
            btnLogIn.setOnClickListener {
                findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)
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
            btnLogIn.spannableText(25, btnLogIn.text.length, resources.getColor(R.color.main, null))
        }
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            viewModel.addUserLiveData.collect {
                if (it) {
                    findNavController().navigate(R.id.action_signUpFragment_to_openFragment)
                } else {
                    requireContext().toast("Error")
                }
            }
        }
    }

}
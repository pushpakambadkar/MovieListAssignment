package com.example.movielistassignment.presentation.login

import android.os.Bundle
import android.text.InputFilter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.movielistassignment.MainActivity
import com.example.movielistassignment.R
import com.example.movielistassignment.databinding.FragmentLoginBinding
import com.example.movielistassignment.extension.isValidEmail

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        addListeners()
    }

    private fun setToolbar() {
        val toolbar = (requireActivity() as MainActivity).toolbar
        toolbar.title = getString(R.string.str_login_screen)
        toolbar.navigationIcon = null
    }

    private fun addListeners() {
        binding.tiePassword.filters = binding.tiePassword.filters.let {
            it + InputFilter { source, _, _, _, _, _ ->
                source.filterNot { char -> char.isWhitespace() }
            }
        }
        binding.tieEmail.addTextChangedListener {
            it?.let {
                if (it.toString().isValidEmail().not()) {
                    binding.tilEmail.error = getString(R.string.str_email_error)
                    binding.loginBtn.isEnabled = false
                } else {
                    binding.tilEmail.error = ""
                    isValidForm()
                }
            }
        }

        binding.tiePassword.addTextChangedListener {
            it?.let {
                if (it.toString().length !in 8..15) {
                    binding.tilPassword.error = getString(R.string.str_password_error)
                    binding.loginBtn.isEnabled = false
                } else {
                    binding.tilPassword.error = ""
                    isValidForm()
                }
            }
        }

        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bottomSheetSampleFragment)
        }
    }

    private fun isValidForm() {
        if (binding.tieEmail.text?.toString()?.isValidEmail() == true &&
            (binding.tiePassword.text?.toString()?.length ?: 0) in 8..15
        ) {
            binding.loginBtn.isEnabled = true
            return
        }
        binding.loginBtn.isEnabled = false
    }

}
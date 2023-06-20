package com.example.bgproject.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bgproject.R
import com.example.bgproject.databinding.FragmentSignUpBinding
import com.example.bgproject.model.User
import com.example.bgproject.viewmodel.UserViewModel
import kotlinx.coroutines.launch


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]


        binding.btnSignUp.setOnClickListener {
            val fullName = binding.etFullname.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()


//            if (fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            if (fullName.length < 5) {
                Toast.makeText(
                    requireContext(), "Name should be at least 5 characters long",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(
                    requireContext(), "Invalid email format",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (password.length < 5) {
                Toast.makeText(
                    requireContext(), "Password should be at least 5 characters long",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                lifecycleScope.launch {
                    if (password == confirmPassword) {
                        mUserViewModel.alreadyUser(email)
                        if (mUserViewModel.user != null) {
                            Toast.makeText(context, "User already exist", Toast.LENGTH_SHORT).show()
                            return@launch
                        }

                        mUserViewModel.user =
                            User(officerId = generateOfficerId(), fullName, email, password)

                        mUserViewModel.registerUser()
                        Toast.makeText(
                            context,
                            "User Successfully Registered",
                            Toast.LENGTH_SHORT
                        ).show()
                        mUserViewModel.user = null
                        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                    } else {
                        Toast.makeText(
                            context,
                            "please enter the same password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding.llLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    private fun generateOfficerId(): String {
        return "training_officer_${System.currentTimeMillis()}"
    }
}
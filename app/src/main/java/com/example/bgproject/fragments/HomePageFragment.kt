package com.example.bgproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bgproject.R
import com.example.bgproject.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRecruit.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_recruitmentFragment)
        }

        binding.btnRegisterTgl.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_tglsFragment)
        }

        binding.btnConTest.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_conductTestFragment)
        }
    }


}
package com.example.bgproject.fragments.recruitment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bgproject.R
import com.example.bgproject.databinding.FragmentTglsBinding
import com.example.bgproject.model.Tgl
import com.example.bgproject.viewmodel.UserViewModel


class TglsFragment : Fragment(), TglAdapter.OnStateChangeListener{

    private lateinit var binding: FragmentTglsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTglsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TglAdapter(this)
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getTglByUser.observe(viewLifecycleOwner, Observer { tgl ->
            adapter.setData(tgl)
        })

        binding.btnSignOut.setOnClickListener {
            findNavController().navigate(R.id.action_tglsFragment_to_signInFragment)
        }

        binding.btnNewTglRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_tglsFragment_to_recruitmentFragment)
        }


    }

    override fun onTestStarted(tgl: Tgl) {
        userViewModel.updateTgl(tgl)
    }



}
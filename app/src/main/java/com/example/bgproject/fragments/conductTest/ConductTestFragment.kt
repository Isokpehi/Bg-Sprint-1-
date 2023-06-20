package com.example.bgproject.fragments.conductTest

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bgproject.R
import com.example.bgproject.databinding.FragmentConductTestBinding
import com.example.bgproject.model.Tgl
import com.example.bgproject.viewmodel.UserViewModel
import kotlinx.coroutines.launch


class ConductTestFragment : Fragment(), TestAdapter.RecyclerViewClickListener, TestAdapter.ResultClickListener
{

    private lateinit var binding: FragmentConductTestBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentConductTestBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TestAdapter(this, this)
        recyclerView = binding.recyclerView2
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        userViewModel.scheduleTgl.observe(viewLifecycleOwner) { tgl ->
            adapter.setData(tgl)
        }
    }

    override fun onItemClick(tgl: Tgl) {
        userViewModel.updateTgl(tgl)
        val action = ConductTestFragmentDirections.actionConductTestFragmentToQuestionsFragment(tgl.tglId)
        findNavController().navigate(action)
    }

    override fun onResultClick(tgl: Tgl) {
        val action1 = ConductTestFragmentDirections.actionConductTestFragmentToResultFragment2(tgl.tglId)
        findNavController().navigate(action1)
    }


}
package com.example.bgproject.fragments.result

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bgproject.R
import com.example.bgproject.databinding.FragmentResultBinding
import com.example.bgproject.fragments.question.Question
import com.example.bgproject.fragments.question.QuestionsFragmentArgs
import com.example.bgproject.model.Response
import com.example.bgproject.viewmodel.UserViewModel


class ResultFragment : Fragment() {

    private lateinit var adapter: ResultAdapter
    private lateinit var binding: FragmentResultBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var userViewModel: UserViewModel
    private lateinit var question: ArrayList<Question>
    private val args by navArgs<ResultFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        userViewModel.getResultByTglId(args.tglId)

        val myDataset = userViewModel.getQuestions()
        question = myDataset

        adapter = ResultAdapter()
        recyclerView = binding.resultRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel.getResultByTgl.observe(viewLifecycleOwner, Observer { response ->
            adapter.setData(response.result)
        })

        userViewModel.getResultByTgl.observe(viewLifecycleOwner, Observer { response ->
            binding.resultTextView.text = getString(R.string.results, response.score.toString())
        })

    }



}
package com.example.bgproject.fragments.question

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bgproject.R
import com.example.bgproject.data.ResultTypeConverter
import com.example.bgproject.databinding.FragmentQuestionsBinding
import com.example.bgproject.fragments.recruitment.UpdateFragmentArgs
import com.example.bgproject.model.Response
import com.example.bgproject.model.Result
import com.example.bgproject.model.Tgl
import com.example.bgproject.viewmodel.UserViewModel
import kotlinx.coroutines.launch


class QuestionsFragment : Fragment() {

    private lateinit var adapter: QuestionAdapter
    private lateinit var binding: FragmentQuestionsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: UserViewModel
    private lateinit var tgl: Tgl
    private lateinit var resultTypeConverter: ResultTypeConverter
    private val args by navArgs<QuestionsFragmentArgs>()
    val resultList: MutableList<Result> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        resultTypeConverter = ResultTypeConverter()

        val myDataset = viewModel.getQuestions()

        adapter = QuestionAdapter(myDataset, viewModel)
        recyclerView = binding.questionRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        binding.btnSubmit.setOnClickListener {
            lifecycleScope.launch {
                val userSelections = viewModel.getSelectedOptions()
                if (userSelections.size != viewModel.getQuestions().size) {
                    Toast.makeText(context, "please answer all question", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                val score = viewModel.evaluateAnswers(myDataset)
//            displayScore(score)
                val results = adapter.getResults()

                val response = Response(args.tglId, results,score)
                if (response != null) {
                    viewModel.registerResult(response)
                }
                val action =
                    QuestionsFragmentDirections.actionQuestionsFragmentToResultFragment(args.tglId)
                findNavController().navigate(action)
            }

        }

    }

//    private fun displayScore(score: Int) {
//        binding.scoreTextView.text = "Score: $score/${adapter.itemCount}"
//    }


}


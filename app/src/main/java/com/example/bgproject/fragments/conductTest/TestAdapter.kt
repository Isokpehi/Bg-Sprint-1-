package com.example.bgproject.fragments.conductTest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bgproject.R
import com.example.bgproject.model.Tgl

class TestAdapter(private val listener: RecyclerViewClickListener, private val result: ResultClickListener) :
    RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    private var tglList = emptyList<Tgl>()



    inner class TestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fullName: TextView = view.findViewById(R.id.tvFullName)
        val sex: TextView = view.findViewById(R.id.tvSex)
        val state: TextView = view.findViewById(R.id.tvState)
        val test: Button = view.findViewById(R.id.btnTest)
        val result : Button = view.findViewById(R.id.btnResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.test_list, parent, false)
        return TestViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return tglList.size
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val currentItem = tglList[position]
        holder.fullName.text = currentItem.fullName
        holder.sex.text = currentItem.sex
        holder.state.text = currentItem.state

        holder.test.setOnClickListener {
            listener.onItemClick(currentItem.copy(testFlag = 2))
        }

        holder.test.visibility = if (currentItem.testFlag == 2) View.INVISIBLE else View.VISIBLE
        holder.result.visibility = if (currentItem.testFlag == 2) View.VISIBLE else View.INVISIBLE

        holder.result.setOnClickListener {
            result.onResultClick(currentItem)
        }
    }

    fun setData(tgl: List<Tgl>) {
        this.tglList = tgl
        notifyDataSetChanged()
    }

    interface RecyclerViewClickListener {
        fun onItemClick(tgl: Tgl)
    }

    interface ResultClickListener{
        fun onResultClick(tgl: Tgl)
    }

}

package com.example.bgproject.fragments.recruitment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bgproject.R
import com.example.bgproject.model.Tgl

class TglAdapter(
    private val onStateChangeListener: OnStateChangeListener
) : RecyclerView.Adapter<TglAdapter.TglViewHolder>() {

    private var tglList = emptyList<Tgl>()

    class TglViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val fullName: TextView = view.findViewById(R.id.tvFullName)
        val sex: TextView = view.findViewById(R.id.tvSex)
        val state: TextView = view.findViewById(R.id.tvState)
        val edit: Button = view.findViewById(R.id.btnEdit)
        val schedule : Button = view.findViewById(R.id.btnSchedule)
        val alreadyScheduled : TextView = view.findViewById(R.id.alreadyScheduled)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TglViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.tgl_list, parent, false)
        return TglViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return tglList.size
    }

    override fun onBindViewHolder(holder: TglViewHolder, position: Int) {
        val currentItem = tglList[position]
        holder.fullName.text = currentItem.fullName
        holder.sex.text = currentItem.sex
        holder.state.text = currentItem.state
        holder.edit.setOnClickListener {
            val action = TglsFragmentDirections.actionTglsFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.schedule.setOnClickListener {
            onStateChangeListener.onTestStarted(currentItem.copy(testFlag = 1))
        }
        holder.edit.visibility =  if (currentItem.testFlag == 1 || currentItem.testFlag == 2) View.INVISIBLE else View.VISIBLE
        holder.schedule.visibility = if (currentItem.testFlag == 1 || currentItem.testFlag == 2) View.INVISIBLE else View.VISIBLE
        holder.alreadyScheduled.visibility = if (currentItem.testFlag == 1 || currentItem.testFlag == 2) View.VISIBLE else View.INVISIBLE

    }


    fun setData(tgl: List<Tgl>) {
        this.tglList = tgl
        notifyDataSetChanged()
    }

    interface OnStateChangeListener{
        fun onTestStarted(tgl: Tgl)
    }
}




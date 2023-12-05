package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.databinding.ItemKonsultasiDkBinding
import com.collaboracrew.catwell.model.PatientHistoryModel



class PatientListAdapter(
    private val patientHistoryList: List<PatientHistoryModel>,
    private val clickListener: (PatientHistoryModel) -> Unit,
) : RecyclerView.Adapter<PatientListAdapter.AcceptViewHolder>() {

    inner class AcceptViewHolder(private val binding: ItemKonsultasiDkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: PatientHistoryModel) {
            with(binding) {
                iVfotoprofileDk.setImageResource(history.imageProfile)
                tvDoctorName.text = history.patientName
                tvKucingName.text = "kucing oyen"
                tvTime.text = history.time
                tvJam.text = "${history.day}, ${history.date}"

                baseCover.setOnClickListener {
                    clickListener(history)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcceptViewHolder {
        val binding = ItemKonsultasiDkBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AcceptViewHolder(binding)
    }

    override fun getItemCount(): Int = patientHistoryList.size

    override fun onBindViewHolder(holder: AcceptViewHolder, position: Int) {
        val history = patientHistoryList[position]
        holder.bind(history)
    }
}

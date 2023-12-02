package com.collaboracrew.catwell.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.databinding.ListAcceptConsultationBinding
import com.collaboracrew.catwell.databinding.ListRejectConsultationBinding
import com.collaboracrew.catwell.model.PatientHistoryModel



class PatientHistoryAdapter(
    private val patientHistoryList: List<PatientHistoryModel>,
    private val clickListener: (PatientHistoryModel) -> Unit,
    private val isFromAcceptConsultationFragment: Boolean

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ACCEPT = 1
        private const val VIEW_TYPE_REJECT = 2
    }

    inner class AcceptViewHolder(private val binding: ListAcceptConsultationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: PatientHistoryModel) {
            with(binding) {
                ivPatient.setImageResource(history.imageProfile)
                tvName.text = history.patientName
                tvSchedule.text = "${history.day}, ${history.date}"
                tvTime.text = "Pukul ${history.time} WIB"
                tvPayment.text = "Pembayaran via ${history.payment} sebesar Rp${history.price}"

                layoutSchedule.setOnClickListener {
                    clickListener(history)
                }
            }
        }
    }

    inner class RejectViewHolder(private val binding: ListRejectConsultationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: PatientHistoryModel) {
            with(binding) {
                ivPatient.setImageResource(history.imageProfile)
                tvName.text = history.patientName
                tvSchedule.text = "${history.day}, ${history.date}"
                tvTime.text = "Pukul ${history.time} WIB"
                tvPayment.text = "Pembayaran via ${history.payment} sebesar Rp${history.price}"

                layoutSchedule.setOnClickListener {
//                    clickListener(history)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ACCEPT) {
            val binding = ListAcceptConsultationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            AcceptViewHolder(binding)
        } else {
            val binding = ListRejectConsultationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            RejectViewHolder(binding)
        }
    }

    override fun getItemCount(): Int = patientHistoryList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val history = patientHistoryList[position]

        if (holder is AcceptViewHolder && isFromAcceptConsultationFragment) {
            holder.bind(history)
        } else if (holder is RejectViewHolder && !isFromAcceptConsultationFragment) {
            holder.bind(history)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isFromAcceptConsultationFragment) {
            VIEW_TYPE_ACCEPT
        } else {
            VIEW_TYPE_REJECT
        }
    }
}

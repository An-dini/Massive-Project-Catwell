package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.databinding.ItemHistoryChatBinding
import com.collaboracrew.catwell.model.ChatPatientHistoryModel

class RiwayatChatAdapter(private val transaction: List<ChatPatientHistoryModel>, private val clickListener: ChatClickListener) :
    RecyclerView.Adapter<RiwayatChatAdapter.ViewHolder>()
{
    class ViewHolder(val binding: ItemHistoryChatBinding) : RecyclerView.ViewHolder(binding.root)
    interface ChatClickListener {
        fun onClick(transaction: ChatPatientHistoryModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemHistoryChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = transaction.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        with(holder){
            with(transaction[position]){
                binding.ivPatient.setImageResource(this.imageProfile)
                binding.tvNama.text = this.patientName
                binding.tvChatIsi.text = this.isiChat

                binding.clTransaction.setOnClickListener {
                    clickListener.onClick(this)
                }
            }
        }
    }
}


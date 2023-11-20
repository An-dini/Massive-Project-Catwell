package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.databinding.ItemHistoryConsultationBinding
import com.collaboracrew.catwell.model.RiwayatModel
import java.text.NumberFormat
import java.util.Locale

class RiwayatAdapter(private val transaction: List<RiwayatModel>, private val clickListener: TransactionClickListener) :
    RecyclerView.Adapter<RiwayatAdapter.ViewHolder>()
{
    class ViewHolder(val binding: ItemHistoryConsultationBinding) : RecyclerView.ViewHolder(binding.root)
    interface TransactionClickListener {
        fun onClick(transaction: RiwayatModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemHistoryConsultationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = transaction.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        with(holder){
            with(transaction[position]){
                val formattedPrice = NumberFormat.getNumberInstance(Locale("id", "ID")).format(this.price)
                val transactionDescription = "Pembayaran via ${this.paymentMethod} sebesar Rp$formattedPrice"

                binding.tvDate.text = this.date
                binding.tvTime.text = this.time
                binding.tvPayment.text = transactionDescription

                binding.clTransaction.setOnClickListener {
                    clickListener.onClick(this)
                }
            }
        }
    }
}


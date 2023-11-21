package com.collaboracrew.catwell.viewmodel

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.DateModel

class DateAdapter(private val dateList: List<DateModel>) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    private var selectedItemPosition: Int = -1

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setSelectedItemPosition(position: Int) {
        selectedItemPosition = position
        notifyDataSetChanged()
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView);
        val dayTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val dayOfWeekTextView: TextView = itemView.findViewById(R.id.dayOfWeekTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.date_item, parent, false)
        return DateViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val date = dateList[position]
        holder.dayTextView.text = date.date
        holder.dayOfWeekTextView.text = date.dayOfWeek

        // Set latar belakang item berdasarkan kondisi pemilihan
        if (position == selectedItemPosition) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FEEBD5")) // Mengganti latar belakang saat item dipilih
            holder.dayTextView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.PrimaryColor))
            holder.dayOfWeekTextView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.PrimaryColor))
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))  // Mengembalikan latar belakang ke kondisi awal
            holder.dayTextView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.black))
            holder.dayOfWeekTextView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.black))
        }

        holder.itemView.setOnClickListener {
            val clickedPosition = holder.adapterPosition
            setSelectedItemPosition(clickedPosition)
            listener?.onItemClick(clickedPosition)
        }

    }

    override fun getItemCount(): Int {
        return dateList.size
    }
}

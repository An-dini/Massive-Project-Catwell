package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.TimeModel

class TimeAdapter(private val timeList: List<TimeModel>) : RecyclerView.Adapter<TimeAdapter.TimeViewHolder>() {

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_time, parent, false)
        return TimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        val time = timeList[position]
        holder.timeTextView.text = time.time

        // Set latar belakang item berdasarkan kondisi pemilihan
        if (position == selectedItemPosition) {
            holder.itemView.setBackgroundResource(R.drawable.rectangle_rounded_selected) // Mengganti latar belakang saat item dipilih
            holder.timeTextView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        } else {
            holder.itemView.setBackgroundResource(R.drawable.rectangle_rounded_stroke) // Mengembalikan latar belakang ke kondisi awal
            holder.timeTextView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.PrimaryColor))
        }

        holder.itemView.setOnClickListener {
            val clickedPosition = holder.adapterPosition
            setSelectedItemPosition(clickedPosition)
            listener?.onItemClick(clickedPosition)
        }
    }



    override fun getItemCount(): Int {
        return timeList.size
    }

    inner class TimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
    }
}

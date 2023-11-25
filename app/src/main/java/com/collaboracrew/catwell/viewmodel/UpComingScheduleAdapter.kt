package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.UpComingScheduleItem

class UpComingScheduleAdapter(private val data: List<UpComingScheduleItem>) : RecyclerView.Adapter<UpComingScheduleAdapter.ViewHolder>() {

    private var onItemClickListener: ((UpComingScheduleItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (UpComingScheduleItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_upcoming_schedule, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val statusLabel: TextView = itemView.findViewById(R.id.tvStatus)
        private val doctorImage: ImageView = itemView.findViewById(R.id.ivDoctor)
        private val doctorName: TextView = itemView.findViewById(R.id.tvDoctorName)
        private val vetName: TextView = itemView.findViewById(R.id.tvVetName)
        private val dateText: TextView = itemView.findViewById(R.id.tvDate)
        private val timeText: TextView = itemView.findViewById(R.id.tvTime)

        fun bind(item: UpComingScheduleItem) {
            statusLabel.text = item.status
            doctorImage.setImageResource(item.doctorImage)
            doctorName.text = item.doctorName
            vetName.text = item.vetName
            dateText.text = item.date
            timeText.text = item.time

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(data[position])
                }
            }
        }
    }
}

package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.ArticleRecommendationModel
import com.collaboracrew.catwell.model.DoctorRecommendationModel

class DoctorRecomAdapter(private val data: List<DoctorRecommendationModel>) :
    RecyclerView.Adapter<DoctorRecomAdapter.ViewHolder>() {

    private var onItemClickListener: ((DoctorRecommendationModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (DoctorRecommendationModel) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor_recommendation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Initialize views from the layout
        private val doctorImage: ImageView = itemView.findViewById(R.id.ivDoctor)
        private val doctorName: TextView = itemView.findViewById(R.id.tvDoctorName)
        private val vetName: TextView = itemView.findViewById(R.id.tvVetName)
        private val price: TextView = itemView.findViewById(R.id.tvPrice)
        private val chatButton: Button = itemView.findViewById(R.id.btKonsultasi)

        fun bind(item: DoctorRecommendationModel) {
            // Set data to the views
            doctorImage.setImageResource(item.doctorImage)
            doctorName.text = item.doctorName
            vetName.text = item.vetName
            price.text = item.price

            chatButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(data[position])
                }
            }
        }
    }
}
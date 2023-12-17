package com.collaboracrew.catwell.view

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.DoctorModel
import com.squareup.picasso.Picasso

class DoctorListAdapter(
    private val dokter: ArrayList<DoctorModel.Data>,
    private val listener: OnAdapterListener
) : RecyclerView.Adapter<DoctorListAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_doctor, parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dokter.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dokter[position]
        holder.nama_dokter.text = data.nama_dokter
        holder.tpt_praktek.text = data.tpt_praktek

        data.foto_dokter?.let { imageUrl ->
            Picasso.get().load(imageUrl).into(holder.foto_dokter)
        }

        holder.foto_dokter.setOnClickListener {
            listener.onClick(data)
        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val foto_dokter = itemView.findViewById<ImageView>(R.id.ivCover)
        val nama_dokter = itemView.findViewById<TextView>(R.id.tvTitleDoctor)
        val tpt_praktek = itemView.findViewById<TextView>(R.id.tvVetDokter)
    }

    fun setDokter(data: List<DoctorModel.Data>){
        dokter.clear()
        dokter.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(doctor: DoctorModel.Data)
    }
}


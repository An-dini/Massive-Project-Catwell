package com.collaboracrew.catwell.viewmodel

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.DoctorModel
import com.collaboracrew.catwell.view.PembayaranChat
import com.squareup.picasso.Picasso


interface DoctorRecomItemClickListener {
    fun onDoctorRecomItemClick(doctorId: String)
}
class DoctorRecomAdapter(
    private val doctors: ArrayList<DoctorModel.Data>,
) : RecyclerView.Adapter<DoctorRecomAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor_recommendation, parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount() = doctors.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = doctors[position]
        holder.tvNamaDokter.text = data.nama_dokter
        holder.tvInstansi.text = data.tpt_praktek

        data.foto_dokter?.let { imageUrl ->
            Picasso.get().load(imageUrl).into(holder.ivDokter)
        }

//        pindah halaman
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, PembayaranChat::class.java)
            intent.putExtra("id_dokter", data.id_dokter)
            it.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivDokter = itemView.findViewById<ImageView>(R.id.ivDoctor)
        val tvNamaDokter = itemView.findViewById<TextView>(R.id.tvDoctorName)
        val tvInstansi = itemView.findViewById<TextView>(R.id.tvVetName)
    }

    fun setData(data: List<DoctorModel.Data>){
        doctors.clear()
        doctors.addAll(data)
        notifyDataSetChanged()
    }
}
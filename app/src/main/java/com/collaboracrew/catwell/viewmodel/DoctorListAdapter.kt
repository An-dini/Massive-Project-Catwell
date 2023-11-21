package com.collaboracrew.catwell.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.databinding.ItemListDoctorBinding
import com.collaboracrew.catwell.model.DoctorModel

class DoctorListAdapter(private val doctors: List<DoctorModel>, private val clickListener: DoctorClickListener) :
    RecyclerView.Adapter<DoctorListAdapter.ViewHolder>()
{
    class ViewHolder(val binding: ItemListDoctorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = ItemListDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = doctors.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        with(holder){
            with(doctors[position]){
                binding.cover.setImageResource(this.photo)
                binding.title.text = this.name
                binding.author.text = this.instance

                binding.cardView.setOnClickListener{
                    clickListener.onClick(this)
                }
            }
        }
    }
}

interface DoctorClickListener {
    fun onClick(doctor: DoctorModel)
}


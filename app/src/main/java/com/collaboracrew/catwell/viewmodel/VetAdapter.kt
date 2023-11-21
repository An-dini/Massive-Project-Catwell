package com.collaboracrew.catwell.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.databinding.ListVetBinding
import com.collaboracrew.catwell.model.VetData


class VetAdapter(private val vetlist: List<VetData>, private val clickListener: (VetData) -> Unit) :
    RecyclerView.Adapter<VetAdapter.ViewHolder>()
{
    class ViewHolder(val binding: ListVetBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListVetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = vetlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(vetlist[position]) {
                binding.imgVet.setImageResource(this.image)
                binding.namaVet.text = this.namavet
                binding.deskripsiVet.text = this.deskripsi

                binding.layoutVet.setOnClickListener {
                    clickListener(this)
                }
            }
        }
    }
}

package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.databinding.ListPuskeswanBinding
import com.collaboracrew.catwell.model.PuskeswanData

class PuskeswanAdapter(private val pswlist: List<PuskeswanData>, private val clickListener: (PuskeswanData) -> Unit) :
    RecyclerView.Adapter<PuskeswanAdapter.ViewHolder>()
{
    class ViewHolder(val binding: ListPuskeswanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListPuskeswanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = pswlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(pswlist[position]) {
                binding.imgPsw1.setImageResource(this.image)
                binding.namaVet.text = this.namapsw
                binding.deskripsiVet.text = this.deskripsi

                binding.layoutPsw.setOnClickListener {
                    clickListener(this)
                }
            }
        }
    }
}

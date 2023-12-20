package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.collaboracrew.catwell.databinding.ListPuskeswanBinding
import com.collaboracrew.catwell.model.Puskeswan_Model

class PuskeswanAdapter(
    private val pswlist: ArrayList<Puskeswan_Model.Result>,
    private val clickListener: (Puskeswan_Model.Result) -> Unit):
    RecyclerView.Adapter<PuskeswanAdapter.ViewHolder>()
{
    inner class ViewHolder(val binding: ListPuskeswanBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(result: Puskeswan_Model.Result) {
            with(binding) {
                NamaPuskeswan.text = result.Nama_Puskeswan
                Wakktu.text = result.Waktu_Buka
                deskripsiVet.text = result.Alamat


                Glide.with(binding.root)
                    .load(result.Img_Puskeswan)
                    .into(imgPuskeswan)

                binding.layoutPsw.setOnClickListener {
                    clickListener(result)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListPuskeswanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = pswlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pswlist[position])
    }

    fun setData(newList: ArrayList<Puskeswan_Model.Result>) {
        pswlist.clear()
        pswlist.addAll(newList)
        notifyDataSetChanged()
    }
}


package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.DataProfilKucing

class AdapterProfilKucing(private val dataList: ArrayList<DataProfilKucing>): RecyclerView.Adapter<AdapterProfilKucing.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_profil_kucing,parent,false)
        return ViewHolderClass(itemView)
    }



    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.PfImage.setImageResource(currentItem.ProfileImage)
        holder.PfName.text= currentItem.ProfileName
        holder.BtnDelte.setImageResource(currentItem.BtnDelete)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView) {
        val PfImage:ImageView = itemView.findViewById(R.id.ivProfilKucing)
        val PfName:TextView = itemView.findViewById(R.id.ivNameKucing)
        val BtnDelte:ImageView = itemView.findViewById(R.id.btn_delete)

    }
}
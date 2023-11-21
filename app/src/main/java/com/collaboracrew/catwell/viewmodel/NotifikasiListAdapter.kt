package com.collaboracrew.catwell.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R

class NotifikasiListAdapter (private val listNotif: List<String>
): RecyclerView.Adapter<NotifikasiListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_notif, parent, false)
    )

    override fun onBindViewHolder(holder: NotifikasiListAdapter.ViewHolder, position: Int) {
        holder.text.text = listNotif[position]
    }

    override fun getItemCount() = listNotif.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val text = view.findViewById<TextView>(R.id.iv_notif)
    }

}
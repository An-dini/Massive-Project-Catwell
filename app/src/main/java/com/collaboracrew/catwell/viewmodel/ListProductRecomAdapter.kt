package com.collaboracrew.catwell.viewmodel

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.ProductRecommendationModel
import com.squareup.picasso.Picasso

class ListProductRecomAdapter(
    val produk: ArrayList<ProductRecommendationModel.Data>
): RecyclerView.Adapter<ListProductRecomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount() = produk.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = produk[position]
//        holder.tvKategori.text = data.kategori
        holder.tvTitle.text = data.nama_produk

        data.foto_produk?.let { imageUrl ->
            Picasso.get().load(imageUrl).into(holder.ivProduk)
        }

//        pindah halaman
//        holder.itemView.setOnClickListener {
//            val intent = Intent(it.context, Login::class.java)
//            intent.putExtra("id_produk", data.id)
//            it.context.startActivity(intent)
//        }

//        link
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.link_produk))
            it.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivProduk = itemView.findViewById<ImageView>(R.id.ivProduct)
        //        val tvKategori = itemView.findViewById<TextView>(R.id.tvKategori)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvProductName)
    }

    fun setData(data: List<ProductRecommendationModel.Data>){
        produk.clear()
        produk.addAll(data)
        notifyDataSetChanged()
    }
}
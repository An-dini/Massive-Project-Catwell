package com.collaboracrew.catwell.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.ListArticleRecommendationModel
import com.collaboracrew.catwell.viewmodel.ListArticlesRecomAdapter

class HalamanListArtikel : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var recyclerListProdukAdapter: ListArticlesRecomAdapter? = null
    private var produkList = mutableListOf<ListArticleRecommendationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_list_artikel)

        val backButton = findViewById(R.id.ivBack) as ImageView
        backButton.setOnClickListener {
            onBackPressed()
        }

        produkList = ArrayList()

        recyclerView = findViewById<View>(R.id.rvListProduk) as RecyclerView
        recyclerListProdukAdapter = ListArticlesRecomAdapter(produkList)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerListProdukAdapter

        prepareDataProduk()
    }

    private fun prepareDataProduk() {
        produkList.apply {
            add(ListArticleRecommendationModel(R.drawable.cat_1, "5 Alergi yang Biasa Dialami Kucing", "Artikel"))
            add(ListArticleRecommendationModel(R.drawable.img_kucing_berantem, "Kucing Luka Karena Berkelahi", "Artikel"))
            add(ListArticleRecommendationModel(R.drawable.cat_3, "7 Alasan untuk Mengganti Pakan Hewan Kamu", "Artikel"))
            add(ListArticleRecommendationModel(R.drawable.cat_2, "Kenapa Kutu Sangat Susah untuk Dihilangkan?", "Artikel"))
            add(ListArticleRecommendationModel(R.drawable.cat_4, "3 Rekomendasi Makanan Diet untuk Kucing", "Perawatan"))
            add(ListArticleRecommendationModel(R.drawable.cat_5, "Apakah Obat Kutu Berbahaya bagi Kucing?", "Artikel"))
            add(ListArticleRecommendationModel(R.drawable.cat_6, "Manfaat Minyak Ikan untuk Kesehatan Bulu Kucing", "Perawatan"))


        }

        recyclerListProdukAdapter?.notifyDataSetChanged()
    }
}

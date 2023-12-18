package com.collaboracrew.catwell.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.ProductRecommendationModel
import com.collaboracrew.catwell.viewmodel.ListProductRecomAdapter

class HalamanProduk : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var recyclerListProdukAdapter: ListProductRecomAdapter? = null
    private var produkList = mutableListOf<ProductRecommendationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_produk)

        val backButton = findViewById(R.id.ivBack) as ImageView
        backButton.setOnClickListener {
            onBackPressed()
        }
        val ivWishlist = findViewById(R.id.ivWishlist) as ImageView
        ivWishlist.setOnClickListener {
            val intent = Intent(this, WishListku::class.java)
            startActivity(intent)
        }


        produkList = ArrayList()

        recyclerView = findViewById<View>(R.id.rvListProduk) as RecyclerView
        recyclerListProdukAdapter = ListProductRecomAdapter(produkList)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerListProdukAdapter

        prepareDataProduk()

        recyclerListProdukAdapter?.setOnItemClickListener { product ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://shopee.co.id/Royal-Canin-kitten-pouch-LOAF-85gr-makanan-basah-kucing-85gr-i.108031550.14771366690?sp_atk=36440427-a7d1-442f-94fe-dd1e349097e8&xptdk=36440427-a7d1-442f-94fe-dd1e349097e8"))
            startActivity(intent)
        }

    }

    private fun prepareDataProduk() {
        produkList.apply {
            add(ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Cannin Kitten"))
            add(ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Cannin Kitten"))
            add(ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Cannin Kitten"))
            add(ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Cannin Kitten"))
            add(ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Cannin Kitten"))
            add(ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Cannin Kitten"))
            add(ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Cannin Kitten"))
            add(ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Cannin Kitten"))
            add(ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Cannin Kitten"))
            add(ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Cannin Kitten"))
            add(ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Cannin Kitten"))
        }

        recyclerListProdukAdapter?.notifyDataSetChanged()
    }

}
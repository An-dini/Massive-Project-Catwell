package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.api.ApiRetrofit
import com.collaboracrew.catwell.model.ProductRecommendationModel
import com.collaboracrew.catwell.viewmodel.ListProductRecomAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HalamanProduk : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var produkAdapter: ListProductRecomAdapter
    private lateinit var listProduk: RecyclerView

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

        setupList()
    }
    override fun onStart() {
        super.onStart()
        getProduk()
    }

    private fun setupList(){
        listProduk = findViewById(R.id.rvListProduk)
        produkAdapter = ListProductRecomAdapter(arrayListOf())
        listProduk.layoutManager = GridLayoutManager(this, 2)
        listProduk.adapter = produkAdapter
    }


    private fun getProduk(){
        api.dataProduk().enqueue(object : Callback<ProductRecommendationModel> {
            override fun onFailure(call: Call<ProductRecommendationModel>, t: Throwable) {
                Log.e("MainActivity", t.toString())
            }

            override fun onResponse(call: Call<ProductRecommendationModel>, response: Response<ProductRecommendationModel>) {
                if (response.isSuccessful) {
                    val listData = response.body()?.produk ?: emptyList()
                    produkAdapter.setData(listData)
                    Log.e("MainActivity", response.toString())
                }
            }

        })
    }

}
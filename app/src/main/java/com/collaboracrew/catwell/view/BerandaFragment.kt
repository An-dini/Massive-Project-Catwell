package com.collaboracrew.catwell.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.api.ApiRetrofit
import com.collaboracrew.catwell.model.ArticleRecommendationModel
import com.collaboracrew.catwell.model.DoctorModel
import com.collaboracrew.catwell.model.ProductRecommendationModel
import com.collaboracrew.catwell.model.UpComingScheduleItem
import com.collaboracrew.catwell.viewmodel.ArticlesRecomAdapter
import com.collaboracrew.catwell.viewmodel.DoctorRecomAdapter
import com.collaboracrew.catwell.viewmodel.ListProductRecomAdapter
import com.collaboracrew.catwell.viewmodel.ProductRecomAdapter
import com.collaboracrew.catwell.viewmodel.PromotionsAdapter
import com.collaboracrew.catwell.viewmodel.UpComingScheduleAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BerandaFragment : Fragment() {

    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var produkAdapter: ProductRecomAdapter
    private lateinit var doctorRecomAdapter: DoctorRecomAdapter
    private lateinit var listProduk: RecyclerView
    private lateinit var rvPromotions: RecyclerView
    private lateinit var rvUpComingSchedule: RecyclerView
    private lateinit var rvDoctorRecom: RecyclerView
    private lateinit var rvArticlesRecom: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repeatedArticleData = mutableListOf<ArticleRecommendationModel>()
        repeat(5) {
            repeatedArticleData.addAll(getSampleArticleData())
        }

        val icNotification = view.findViewById<ImageView>(R.id.icNotification)
        val icBookmark = view.findViewById<ImageView>(R.id.icBookmark)
        val icProfile = view.findViewById<ImageView>(R.id.icProfile)

        icNotification.setOnClickListener {
            startActivity(Intent(requireContext(), NotifikasiActivity::class.java))
        }
        icBookmark.setOnClickListener {
            startActivity(Intent(requireContext(), BookmarkActivity::class.java))
        }
        icProfile.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        val btnMoreProduct = view.findViewById<ImageButton>(R.id.btnMoreProduct)
        btnMoreProduct.setOnClickListener {
            startActivity(Intent(requireContext(), HalamanProduk::class.java))
        }
        val btnMoreArticles = view.findViewById<ImageButton>(R.id.btnMoreArticles)
        btnMoreArticles.setOnClickListener {
            startActivity(Intent(requireContext(), HalamanListArtikel::class.java))
        }


        // Inisialisasi RecyclerView
        rvPromotions = view.findViewById(R.id.rvPromotions)
        rvUpComingSchedule = view.findViewById(R.id.rvUpComingSchedule)
        rvArticlesRecom = view.findViewById(R.id.rvArticlesRecom)

        // Set layout manager dan adapter untuk setiap RecyclerView
        rvPromotions.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rvUpComingSchedule.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvArticlesRecom.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        // Set adapter (anda perlu membuat adapter sesuai dengan kebutuhan Anda)
        rvPromotions.adapter = PromotionsAdapter(getSamplePromoData())
        rvUpComingSchedule.adapter = UpComingScheduleAdapter(getSampleScheduleData()).apply {
            setOnItemClickListener { scheduleItem ->
                val intent = Intent(requireContext(), JadwalKonsultasiActivity::class.java)
                startActivity(intent)
            }
        }

//        rvArticlesRecom.adapter = ArticlesRecomAdapter(repeatedArticleData)
        rvArticlesRecom.adapter = ArticlesRecomAdapter(repeatedArticleData).apply {
            setOnItemClickListener { article ->
                val intent = Intent(requireContext(), DetailArtikel::class.java)
                startActivity(intent)
            }
        }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvPromotions)
        snapHelper.attachToRecyclerView(rvUpComingSchedule)


        setupList()
        getDoctor()
    }

//    produk recommendation

    override fun onStart() {
        super.onStart()
        getProduk()
        getDoctor()
    }

    private fun setupList(){
        listProduk = view?.findViewById(R.id.rvProductRecom) ?: return
        rvDoctorRecom = view?.findViewById(R.id.rvDoctorRecom) ?: return

        produkAdapter = ProductRecomAdapter(arrayListOf())
        doctorRecomAdapter = DoctorRecomAdapter(arrayListOf(), object : DoctorRecomAdapter.OnAdapterListener{
            override fun onClick(doctor: DoctorModel.Data) {
                startActivity(Intent(requireContext(), DoctorDetailActivity::class.java)
                    .putExtra("dokter", doctor)
                )
            }

        })

        listProduk.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        listProduk.adapter = produkAdapter

        rvDoctorRecom.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rvDoctorRecom.adapter = doctorRecomAdapter
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

//    doctor recommendation
private fun getDoctor(){
    api.dataDokter().enqueue(object : Callback<DoctorModel> {
        override fun onFailure(call: Call<DoctorModel>, t: Throwable) {
            Log.e("MainActivity", t.toString())
        }

        override fun onResponse(call: Call<DoctorModel>, response: Response<DoctorModel>) {
            if (response.isSuccessful) {
                val listData = response.body()?.dokter ?: emptyList()
                doctorRecomAdapter.setDokter(listData)
                Log.e("MainActivity", response.toString())
            }
        }

    })
}

//    promo
    val getSamplePromoData: () -> List<Int> = {
        listOf(
            R.drawable.promo_hemat,
            R.drawable.promo_vaksin,
        )
    }

    private fun getSampleScheduleData(): List<UpComingScheduleItem> {
        return listOf(
            UpComingScheduleItem("Upcoming", R.drawable.dr_aji, "Drh. Aji Kusuma", "OJ Pet Care, Batam","23 Nov", "13:00 - 14:00")
        )
    }

    private fun getSampleArticleData(): List<ArticleRecommendationModel> {
        return listOf(
            ArticleRecommendationModel(R.drawable.img_kucing_berantem, "Kucing Luka Karena Berkelahi")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

}
package com.collaboracrew.catwell.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.ArticleRecommendationModel
import com.collaboracrew.catwell.model.DOCTOR_ID_EXTRA
import com.collaboracrew.catwell.model.DoctorModel
import com.collaboracrew.catwell.model.ProductRecommendationModel
import com.collaboracrew.catwell.model.UpComingScheduleItem
import com.collaboracrew.catwell.model.doctorList
import com.collaboracrew.catwell.viewmodel.ArticlesRecomAdapter
import com.collaboracrew.catwell.viewmodel.DoctorRecomAdapter
import com.collaboracrew.catwell.viewmodel.ProductRecomAdapter
import com.collaboracrew.catwell.viewmodel.PromotionsAdapter
import com.collaboracrew.catwell.viewmodel.UpComingScheduleAdapter

class BerandaFragment : Fragment() {

    private lateinit var rvPromotions: RecyclerView
    private lateinit var rvUpComingSchedule: RecyclerView
    private lateinit var rvDoctorRecom: RecyclerView
    private lateinit var rvArticlesRecom: RecyclerView
    private lateinit var rvProductRecom: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repeatedArticleData = mutableListOf<ArticleRecommendationModel>()
        repeat(5) {
            repeatedArticleData.addAll(getSampleArticleData())
        }

        val repeatedProductData = mutableListOf<ProductRecommendationModel>()
        repeat(5) {
            repeatedProductData.addAll(getSampleProductData())
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
        rvDoctorRecom = view.findViewById(R.id.rvDoctorRecom)
        rvArticlesRecom = view.findViewById(R.id.rvArticlesRecom)
        rvProductRecom = view.findViewById(R.id.rvProductRecom)

        // Set layout manager dan adapter untuk setiap RecyclerView
        rvPromotions.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rvUpComingSchedule.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvDoctorRecom.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rvArticlesRecom.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rvProductRecom.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        // Set adapter (anda perlu membuat adapter sesuai dengan kebutuhan Anda)
        rvPromotions.adapter = PromotionsAdapter(getSamplePromoData())
        rvUpComingSchedule.adapter = UpComingScheduleAdapter(getSampleScheduleData()).apply {
            setOnItemClickListener { scheduleItem ->
                val intent = Intent(requireContext(), JadwalKonsultasiActivity::class.java)
                startActivity(intent)
            }
        }
        rvDoctorRecom.adapter = DoctorRecomAdapter(getSampleDoctorData()).apply {
            setOnItemClickListener { doctor ->
                val intent = Intent(requireContext(), DoctorDetailActivity::class.java)
                intent.putExtra(DOCTOR_ID_EXTRA, doctor.id)
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

        rvProductRecom.adapter = ProductRecomAdapter(repeatedProductData).apply {
            setOnItemClickListener { productItem ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://shopee.co.id/Royal-Canin-kitten-pouch-LOAF-85gr-makanan-basah-kucing-85gr-i.108031550.14771366690?sp_atk=36440427-a7d1-442f-94fe-dd1e349097e8&xptdk=36440427-a7d1-442f-94fe-dd1e349097e8"))
                startActivity(intent)
            }
        }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvPromotions)
        snapHelper.attachToRecyclerView(rvUpComingSchedule)


    }

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

    private fun getSampleDoctorData(): List<DoctorModel> {
        val doctorNames = resources.getStringArray(R.array.doctor_names)
        val doctorInstances = resources.getStringArray(R.array.doctor_instances)
        val doctorPrice = resources.getString(R.string.doctor_price)
        val doctorSchedule = resources.getString(R.string.doctor_schedule)
        val doctorDuration = resources.getString(R.string.doctor_duration)
        val doctorRating = resources.getStringArray(R.array.doctor_rating)
        val coverList = cover()

        for (i in 0 until 3) {
            val doctor = DoctorModel(
                coverList[i],
                doctorNames[i],
                doctorInstances[i],
                doctorPrice,
                doctorSchedule,
                doctorDuration,
                doctorRating[i].toFloat()
            )
            doctorList.add(doctor)
        }
        return doctorList
    }

    private fun cover():List<Int> = listOf(
        R.drawable.aji,
        R.drawable.mutiara,
        R.drawable.chandra,
        R.drawable.nadine,
        R.drawable.caroline,
        R.drawable.julia,
        R.drawable.aisha,
        R.drawable.nalend,
        R.drawable.lisa,
        R.drawable.annisa,
        R.drawable.nabila,
        R.drawable.dion
    )

    private fun getSampleArticleData(): List<ArticleRecommendationModel> {
        return listOf(
            ArticleRecommendationModel(R.drawable.img_kucing_berantem, "Kucing Luka Karena Berkelahi")
        )
    }

    private fun getSampleProductData(): List<ProductRecommendationModel> {
        return listOf(
            ProductRecommendationModel(R.drawable.royal_canin_kitten, "Royal Canin Kitten")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

}
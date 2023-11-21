package com.collaboracrew.catwell.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.ArticleRecommendationModel
import com.collaboracrew.catwell.model.DoctorRecommendationModel
import com.collaboracrew.catwell.model.ProductRecommendationModel
import com.collaboracrew.catwell.model.UpComingScheduleItem
import com.collaboracrew.catwell.model.VET_ID_EXTRA
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

        val repeatedDoctorData = mutableListOf<DoctorRecommendationModel>()
        repeat(5) {
            repeatedDoctorData.addAll(getSampleDoctorData())
        }

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
        rvUpComingSchedule.adapter = UpComingScheduleAdapter(getSampleScheduleData())
        rvDoctorRecom.adapter = DoctorRecomAdapter(repeatedDoctorData)
        rvArticlesRecom.adapter = ArticlesRecomAdapter(repeatedArticleData)
        rvProductRecom.adapter = ProductRecomAdapter(repeatedProductData)

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

    private fun getSampleDoctorData(): List<DoctorRecommendationModel> {
        return listOf(
            DoctorRecommendationModel(R.drawable.dr_aji, "Drh. Aji Kusuma", "OJ Pet Care, Batam","Rp130.000")
        )
    }

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
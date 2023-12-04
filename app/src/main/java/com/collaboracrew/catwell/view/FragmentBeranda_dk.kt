package com.collaboracrew.catwell.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.ARG_PARAM1
import com.collaboracrew.catwell.ARG_PARAM2
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.ArticleRecommendationModel
import com.collaboracrew.catwell.model.DataBooking
import com.collaboracrew.catwell.model.DataKonsultasi
import com.collaboracrew.catwell.model.DoctorRecommendationModel
import com.collaboracrew.catwell.model.ProductRecommendationModel
import com.collaboracrew.catwell.model.TimeModel
import com.collaboracrew.catwell.viewmodel.ArticlesRecomAdapter
import com.collaboracrew.catwell.viewmodel.BookingAdapter
import com.collaboracrew.catwell.viewmodel.DoctorRecomAdapter
import com.collaboracrew.catwell.viewmodel.ProductRecomAdapter
import com.collaboracrew.catwell.viewmodel.PromotionsAdapter
import com.collaboracrew.catwell.viewmodel.UpComingScheduleAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentBeranda_dk.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentBeranda_dk : Fragment() {
    private lateinit var rv1_beranda_dk:RecyclerView
    private lateinit var rvtime: RecyclerView
    private lateinit var rv2_beranda_dk: RecyclerView
    private lateinit var btnreject: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repeatedDataBooking = mutableListOf<DataBooking>()
        repeat(5) {
            repeatedDataBooking.addAll(getSampleDataBooking())
        }

        val repeatedDataKonsultasi = mutableListOf<DataKonsultasi>()
        repeat(5) {
            repeatedDataKonsultasi.addAll(getSampleDataKonsultasi())
        }

        val repeatedTimeModel = mutableListOf<TimeModel>()
        repeat(5) {
            repeatedTimeModel.addAll(getSampleTimeModel())
        }
        val ivProfile = view.findViewById<ImageView>(R.id.imgView1)
        ivProfile.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileDokter::class.java))
        }

        val

        // Inisialisasi RecyclerView
        rv1_beranda_dk = view.findViewById(R.id.rv1_beranda_dk)
        rv2_beranda_dk = view.findViewById(R.id.rv2_beranda_dk)
        rvtime = view.findViewById(R.id.rvtime)

        // Set layout manager dan adapter untuk setiap RecyclerView
        rv1_beranda_dk.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rv2_beranda_dk.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvtime.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        // Set adapter (anda perlu membuat adapter sesuai dengan kebutuhan Anda)
        rv2_beranda_dk.adapter = BookingAdapter(getSampleDataBooking())
        rv.adapter = UpComingScheduleAdapter(getSampleScheduleData())
        rvDoctorRecom.adapter = DoctorRecomAdapter(repeatedDoctorData)
        rvArticlesRecom.adapter = ArticlesRecomAdapter(repeatedArticleData)
        rvProductRecom.adapter = ProductRecomAdapter(repeatedProductData)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvPromotions)
        snapHelper.attachToRecyclerView(rvUpComingSchedule)

    }

    private fun getSampleTimeModel(): Collection<TimeModel> {

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentBeranda_dk.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                FragmentBeranda_dk().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
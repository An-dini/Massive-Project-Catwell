package com.collaboracrew.catwell.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.FragmentPuskeswanBinding
import com.collaboracrew.catwell.model.PSW_ID_EXTRA
import com.collaboracrew.catwell.model.PuskeswanData
import com.collaboracrew.catwell.viewmodel.PuskeswanAdapter

class PuskeswanFragment : Fragment() {

    private lateinit var binding: FragmentPuskeswanBinding
    private lateinit var adapter: PuskeswanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPuskeswanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val pswDataList = generatePswDataList()
        adapter = PuskeswanAdapter(pswDataList) { pswData ->
            val intent = Intent(requireContext(), Detail_Vet_Puskeswan::class.java)
            intent.putExtra(PSW_ID_EXTRA, pswData.id)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }


    private fun generatePswDataList(): List<PuskeswanData> {
        val pswDataList = mutableListOf<PuskeswanData>()

        pswDataList.add(PuskeswanData(R.drawable.pswbontang, "Puskeswan Bontang", "Alamat: Kota Bontang, Kalimantan Timur 75321, Kec. Bontang Bar., Gn. Telihan, Jl. Letjen S. Parman gang banjar No.rt 29"))
        pswDataList.add(PuskeswanData(R.drawable.pswragunan, "Puskeswan Ragunan", "Alamat:Jl. Harsono RM No.28, RT.9/RW.4, Ragunan, Kec. Ps. Minggu, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12550, Indonesia"))
        pswDataList.add(PuskeswanData(R.drawable.pswmagelang, "Puskeswan Magelang", "Alamat: Jl. Pahlawan No.8, Magelang, Magelang Tengah, Magelang City, Central Java 56116"))

        return pswDataList
    }
}
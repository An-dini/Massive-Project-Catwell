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

        pswDataList.add(PuskeswanData(R.drawable.depok, "Puskeswan Depok", "Alamat: Jl. Raya Pengasinan, Sawangan Lama, Kec. Sawangan, Kota Depok,"))
        pswDataList.add(PuskeswanData(R.drawable.ragunan, "Puskeswan Ragunan", "Alamat:Jl. Harsono RM No.28, RT.9/RW.4, Ragunan, Kec. Ps. Minggu, Kota Jakarta Selatan,"))
        pswDataList.add(PuskeswanData(R.drawable.cibinong, "Puskeswan Cibinong", "Alamat: Jl. KH. Azhari Jl. Kp. Bojong Koneng, Cibinong, Bogor "))

        return pswDataList
    }
}
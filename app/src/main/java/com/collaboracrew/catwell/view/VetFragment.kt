package com.collaboracrew.catwell.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.FragmentVetBinding
import com.collaboracrew.catwell.model.VET_ID_EXTRA
import com.collaboracrew.catwell.model.VetData

class VetFragment : Fragment() {

    private lateinit var binding: FragmentVetBinding
    private lateinit var adapter: VetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val vetDataList = generateVetDataList()
        adapter = VetAdapter(vetDataList) { vetData ->
            val intent = Intent(requireContext(), Detail_Vet_Puskeswan::class.java)
            intent.putExtra(VET_ID_EXTRA, vetData.id)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }


    private fun generateVetDataList(): List<VetData> {
        val vetDataList = mutableListOf<VetData>()

        vetDataList.add(VetData(R.drawable.torovet, "Toro Vet Clinic", "Alamat:Jl. Taman Palem Lestari, RW.13, Cengkareng Bar., Kecamatan Cengkareng, Kota Jakarta Barat, Daerah Khusus Ibukota "))
        vetDataList.add(VetData(R.drawable.bvc, "OJ Pet Care", "Alamat:Ruko Tisenia (Tiban Sentra Niaga), Blok E No, RT.5/RW.6, Tiban Lama, Sekupang, Batam City, Riau Islands 29427"))
        vetDataList.add(VetData(R.drawable.brunopetclinic, "Bruno Pet Clinic", "Alamat: Bruno Pet Clinic, Jalan Teratai 2 No.4, Lubuk Baja Kota, Kec. Lubuk Baja, Kota Batam, Kepulauan Riau 29432"))

        return vetDataList
    }
}

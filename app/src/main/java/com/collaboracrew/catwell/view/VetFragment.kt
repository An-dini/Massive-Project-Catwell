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

        vetDataList.add(VetData(R.drawable.anabul, "Anabul Vat Centre", "Alamat:Jl. Kartini No.22D, Depok, Kec. Pancoran Mas, Kota Depok, Jawa Barat 16431 "))
        vetDataList.add(VetData(R.drawable.amore, "Amore Pet Shop", "Alamat:Jl. Raya Muchtar, Sawangan Lama, Kec. Sawangan, Kota Depok "))
        vetDataList.add(VetData(R.drawable.leovet, "Leo N Vats", "Alamat: Jalan Tole Iskandar Rt 02/20 no 24, Sukamaju, Kec. Cilodong, Kota Depok."))

        return vetDataList
    }
}

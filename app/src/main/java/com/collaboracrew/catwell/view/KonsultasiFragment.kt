package com.collaboracrew.catwell.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.api.ApiRetrofit
import com.collaboracrew.catwell.databinding.FragmentKonsultasiBinding
import com.collaboracrew.catwell.model.DoctorModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KonsultasiFragment : Fragment() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var binding: FragmentKonsultasiBinding
    private lateinit var listDokter: RecyclerView
    private lateinit var doctorAdapter: DoctorListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKonsultasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        getDokter()
    }

    private fun setupList() {
        listDokter = binding.root.findViewById(R.id.listDokter)
        listDokter.layoutManager = LinearLayoutManager(requireContext())
        doctorAdapter = DoctorListAdapter(arrayListOf(), object : DoctorListAdapter.OnAdapterListener{
            override fun onClick(doctor: DoctorModel.Data) {
                startActivity(Intent(requireContext(), DoctorDetailActivity::class.java)
                    .putExtra("dokter", doctor)
                )
            }

        })
        listDokter.adapter = doctorAdapter
    }

    private fun getDokter() {
        api.dataDokter().enqueue(object : Callback<DoctorModel> {
            override fun onFailure(call: Call<DoctorModel>, t: Throwable) {
                Log.e("Konsultasi Fragment", t.toString())
            }

            override fun onResponse(call: Call<DoctorModel>, response: Response<DoctorModel>) {
                if (response.isSuccessful) {
                    val listDokter = response.body()?.dokter ?: emptyList()
                    doctorAdapter.setDokter(listDokter) // Update adapter with retrieved data
                    Log.e("Konsultasi Fragment", response.toString())
                }
            }
        })
    }
}
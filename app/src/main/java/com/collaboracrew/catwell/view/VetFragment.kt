package com.collaboracrew.catwell.view


import VetAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.collaboracrew.catwell.api.ApiEndpoint
import com.collaboracrew.catwell.api.ApiRetrofit
import com.collaboracrew.catwell.databinding.FragmentVetBinding
import com.collaboracrew.catwell.model.Vet_Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VetFragment : Fragment() {

    private lateinit var binding: FragmentVetBinding
    private lateinit var adapter: VetAdapter
    private lateinit var apiService: ApiEndpoint

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiService = ApiRetrofit().endpoint
        setupRecyclerView()
        loadDataFromApi()
    }

    private fun setupRecyclerView() {
        adapter = VetAdapter(ArrayList()) { vetModelResult ->
            when (vetModelResult.ID_Vet) {
                "7" -> {
                    val intent1 = Intent(requireContext(), Detail_Vet_Anabul::class.java)
                    intent1.putExtra("7", vetModelResult.ID_Vet)
                    startActivity(intent1)
                }
                "8" -> {
                    val intent2 = Intent(requireContext(), Detail_Vet_Amore::class.java)
                    intent2.putExtra("8", vetModelResult.ID_Vet)
                    startActivity(intent2)
                }
                "13" -> {
                    val intent3 = Intent(requireContext(), Detail_Vet_Leo::class.java)
                    intent3.putExtra("13", vetModelResult.ID_Vet)
                    startActivity(intent3)
                }
                else -> {
                    val intentDefault = Intent(requireContext(), VetFragment::class.java)
                    startActivity(intentDefault)
                }
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun loadDataFromApi() {
        apiService.DataVet("ID_Vet", "Nama_Vet", "Alamat", "Waktu_Buka","No_Vet","Img_Vet").enqueue(object : Callback<Vet_Model> {
            override fun onResponse(call: Call<Vet_Model>, response: Response<Vet_Model>) {
                if (response.isSuccessful) {
                    val vetDataList = response.body()?.result ?: ArrayList()
                    updateRecyclerView(vetDataList)
                } else {
                    Log.e("API_REQUEST", "Failed to get data")
                }
            }

            override fun onFailure(call: Call<Vet_Model>, t: Throwable) {
                Log.e("API_REQUEST", "Failed to fetch data: ${t.message}")
            }
        })
    }

    private fun updateRecyclerView(vetDataList: ArrayList<Vet_Model.Result>) {
        adapter.apply {
            setData(vetDataList)
            notifyDataSetChanged()
        }
    }
}

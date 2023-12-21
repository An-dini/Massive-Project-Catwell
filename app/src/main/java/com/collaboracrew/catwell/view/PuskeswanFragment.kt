package com.collaboracrew.catwell.view

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
import com.collaboracrew.catwell.databinding.FragmentPuskeswanBinding
import com.collaboracrew.catwell.model.Puskeswan_Model
import com.collaboracrew.catwell.viewmodel.PuskeswanAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PuskeswanFragment : Fragment() {

    private lateinit var binding: FragmentPuskeswanBinding
    private lateinit var adapter: PuskeswanAdapter
    private lateinit var apiService: ApiEndpoint

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPuskeswanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiService = ApiRetrofit().endpoint
        setupRecyclerView()
        loadDataFromApi()
    }

    private fun setupRecyclerView() {
        adapter = PuskeswanAdapter(ArrayList()) { puskeswanResult ->
            when (puskeswanResult.ID_Puskeswan) {
                "1" -> {
                    val intent1 = Intent(requireContext(), Detail_Psw_Depok::class.java)
                    intent1.putExtra("1", puskeswanResult.ID_Puskeswan)
                    startActivity(intent1)
                }
                "2" -> {
                    val intent2 = Intent(requireContext(), Detail_Psw_Ragunan::class.java)
                    intent2.putExtra("2", puskeswanResult.ID_Puskeswan)
                    startActivity(intent2)
                }
                "3" -> {
                    val intent3 = Intent(requireContext(), Detail_Psw_Cibinong::class.java)
                    intent3.putExtra("3", puskeswanResult.ID_Puskeswan)
                    startActivity(intent3)
                }
                else -> {
                    val intentDefault = Intent(requireContext(), PuskeswanFragment::class.java)
                    startActivity(intentDefault)
                }
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun loadDataFromApi() {
        apiService.DataPuskeswan("ID_Puskeswan", "Nama_Puskeswan", "Alamat", "Waktu_Buka", "No_Puskeswan","Img_Puskeswan").enqueue(object : Callback<Puskeswan_Model> {
            override fun onResponse(call: Call<Puskeswan_Model>, response: Response<Puskeswan_Model>) {
                if (response.isSuccessful) {
                    val puskeswanDataList = response.body()?.result ?: ArrayList()
                    updateRecyclerView(puskeswanDataList)
                } else {
                    Log.e("API_REQUEST", "Failed to get data")
                }
            }

            override fun onFailure(call: Call<Puskeswan_Model>, t: Throwable) {
                Log.e("API_REQUEST", "Failed to fetch data: ${t.message}")
            }
        })
    }

    private fun updateRecyclerView(puskeswanDataList: ArrayList<Puskeswan_Model.Result>) {
        adapter.apply {
            setData(puskeswanDataList)
            notifyDataSetChanged()
        }
    }

}

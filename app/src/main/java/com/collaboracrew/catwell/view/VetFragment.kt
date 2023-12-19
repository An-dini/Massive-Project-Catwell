
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
import com.collaboracrew.catwell.view.Detail_Vet_Puskeswan
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
            val intent = Intent(requireContext(), Detail_Vet_Puskeswan::class.java)
            intent.putExtra("ID_Vet", vetModelResult.ID_Vet)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun loadDataFromApi() {
        //
        apiService.DataVet("Nama_Vet", "Alamat", "Waktu_Buka", "No_Vet","Img_Vet").enqueue(object : Callback<Vet_Model> {
            override fun onResponse(call: Call<Vet_Model>, response: Response<Vet_Model>) {
                if (response.isSuccessful) {
                    val vetDataList = response.body()?.result ?: ArrayList()
                    updateRecyclerView(vetDataList)
                } else {

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

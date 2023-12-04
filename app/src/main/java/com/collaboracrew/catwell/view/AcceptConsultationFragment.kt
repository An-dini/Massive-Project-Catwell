package com.collaboracrew.catwell.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.FragmentAcceptConsultationBinding
import com.collaboracrew.catwell.model.PATIENT_HISTORY_ID_EXTRA
import com.collaboracrew.catwell.model.PatientHistoryModel

class AcceptConsultationFragment : Fragment() {
    private lateinit var binding: FragmentAcceptConsultationBinding
    private lateinit var adapter: PatientHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcceptConsultationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateScheduleList()
        setupRecyclerView()
    }



    private fun setupRecyclerView() {
        val patientHistoryList = generateScheduleList()
        adapter = PatientHistoryAdapter(patientHistoryList, clickListener = { patientHistoryList ->
            val intent = Intent(requireContext(), PatientHistoryDetailActivity::class.java)
            intent.putExtra(PATIENT_HISTORY_ID_EXTRA, patientHistoryList.id)
            startActivity(intent)
        }, isFromAcceptConsultationFragment = true)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }


    private fun generateScheduleList(): List<PatientHistoryModel> {
        val patientHistoryList = mutableListOf<PatientHistoryModel>()

        patientHistoryList.add(PatientHistoryModel(R.drawable.aji, "Larasati", "Senin", "13 Agustus 2023", "20:00", "DANA", "130.000", "Muntah-muntah, nafsu makan menurun, lemas", "di beri makanan favorit lalu di kasih obat penambah nafsu makan", "Video Call", "Maropitant citrate (3x1 hari)", "-", 1))
        patientHistoryList.add(PatientHistoryModel(R.drawable.aji, "Budi", "Selsa", "14 Agustus 2023", "10:00", "OVO", "130.000", "Muntah-muntah, nafsu makan menurun, lemas", "di beri makanan favorit lalu di kasih obat penambah nafsu makan", "Video Call", "Maropitant citrate (3x1 hari)", "-", 2))
        patientHistoryList.add(PatientHistoryModel(R.drawable.aji, "Mutiara", "Kamis", "16 Agustus 2023", "12:00", "ShopeePay", "130.000", "Muntah-muntah, nafsu makan menurun, lemas", "di beri makanan favorit lalu di kasih obat penambah nafsu makan", "Video Call", "Maropitant citrate (3x1 hari)", "-", 3))
        patientHistoryList.add(PatientHistoryModel(R.drawable.aji, "Devi", "Jumat", "17 Agustus 2023", "15:00", "DANA", "130.000", "Muntah-muntah, nafsu makan menurun, lemas", "di beri makanan favorit lalu di kasih obat penambah nafsu makan", "Video Call", "Maropitant citrate (3x1 hari)", "-", 4))


        return patientHistoryList
    }
}
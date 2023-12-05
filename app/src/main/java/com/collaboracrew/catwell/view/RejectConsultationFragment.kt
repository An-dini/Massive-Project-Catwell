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
import com.collaboracrew.catwell.databinding.FragmentRejectConsultationBinding
import com.collaboracrew.catwell.model.DOCTOR_ID_EXTRA
import com.collaboracrew.catwell.model.PATIENT_HISTORY_ID_EXTRA
import com.collaboracrew.catwell.model.PatientHistoryModel

class RejectConsultationFragment : Fragment() {
    private lateinit var binding: FragmentRejectConsultationBinding
    private lateinit var adapter: PatientHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRejectConsultationBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        transactions()
    }


    private fun setupRecyclerView() {
        val patientHistoryList = transactions()
        adapter =
            PatientHistoryAdapter(patientHistoryList, clickListener = { selectedPatientHistory ->
                val intent = Intent(requireContext(), PatientHistoryDetailActivity::class.java)
                intent.putExtra(PATIENT_HISTORY_ID_EXTRA, selectedPatientHistory.id)
                startActivity(intent)
            }, isFromAcceptConsultationFragment = true)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun transactions(): List<PatientHistoryModel> {
        val patientHistoryList = mutableListOf<PatientHistoryModel>()
        val patientName = resources.getStringArray(R.array.patient_name_dc)
        val day = resources.getStringArray(R.array.day_dc)
        val date = resources.getStringArray(R.array.date_dc)
        val time = resources.getStringArray(R.array.time_dc)
        val payment = resources.getStringArray(R.array.payment_dc)
        val price = resources.getStringArray(R.array.price_dc)
        val complaint = resources.getStringArray(R.array.complaint_dc)
        val solution = resources.getStringArray(R.array.solution_dc)
        val method = resources.getStringArray(R.array.method_dc)
        val recipe = resources.getStringArray(R.array.recipe_dc)
        val reference = "-"



        for (i in patientName.indices) {
            val user = patientName[i]
            val transaction = PatientHistoryModel(
                coverResource(user),
                user,
                day[i],
                date[i],
                time[i],
                payment[i],
                price[i],
                complaint[i],
                solution[i],
                method[i],
                recipe[i],
                reference,
                id = patientHistoryList.size
            )
            patientHistoryList.add(transaction)
        }
        return patientHistoryList
    }

    private fun coverResource(user: String): Int {
        return when (user) {
            "Budi" -> R.drawable.aji
            "Larasati" -> R.drawable.mutiara
            "Mutiara" -> R.drawable.aisha
            "Devi" -> R.drawable.nadine
            else -> R.drawable.aji
        }
    }
}
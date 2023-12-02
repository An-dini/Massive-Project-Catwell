package com.collaboracrew.catwell.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.collaboracrew.catwell.databinding.PatientHistoryDetailBinding
import com.collaboracrew.catwell.model.PATIENT_HISTORY_ID_EXTRA
import com.collaboracrew.catwell.model.PatientHistoryModel
import com.collaboracrew.catwell.model.patientHistoryList

class PatientHistoryDetailActivity : AppCompatActivity() {
    private lateinit var binding: PatientHistoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PatientHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backButton = binding.ivBack
        backButton.setOnClickListener {
            onBackPressed()
        }

        val historyID = intent.getIntExtra(PATIENT_HISTORY_ID_EXTRA, -1)
        val history = historyFromID(historyID)
        Log.d("PatientHistoryDetail", "Received History ID: $historyID")

        if (history != null) {
            Log.d("PatientHistoryDetail", "Found history for ID $historyID: ${history.patientName}")

            binding.ivPatient.setImageResource(history.imageProfile)
            binding.tvPatientName.text = history.patientName // Updated to set patient name

            binding.tvSchedule.text = "${history.day}, ${history.date}"
            binding.tvTime.text = "${history.time} WIB"
            binding.tvMethod.text = history.method
            binding.tvComplaint.text = history.complaint
            binding.tvSolution.text = history.solution
            binding.tvRecipe.text = history.recipe
            binding.tvReference.text = history.reference
        } else {
            Log.e("PatientHistoryDetail", "History not found for ID: $historyID")
        }
    }

    private fun historyFromID(historyID: Int): PatientHistoryModel? {
        return patientHistoryList.find { it.id == historyID }
    }
}



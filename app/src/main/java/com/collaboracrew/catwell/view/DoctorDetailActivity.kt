package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.ActivityDoctorDetailBinding
import com.collaboracrew.catwell.model.DOCTOR_ID_EXTRA
import com.collaboracrew.catwell.model.DoctorModel
import com.collaboracrew.catwell.model.doctorList

class DoctorDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val doctorID = intent.getIntExtra(DOCTOR_ID_EXTRA, -1)
        val doctor = doctorFromID(doctorID)
        Log.d("DoctorFragment", "Doctor ID: $doctorID")

        if (doctor != null) {
            binding.cover.setImageResource(doctor.photo)
            binding.price.text = doctor.price
            binding.duration.text  = "${doctor.duration} menit"
            binding.name.text = doctor.name
            binding.instance.text = doctor.instance
            binding.ratingBar.rating = doctor.rating
            binding.schedule.text = doctor.schedule

            val btKonsultasi = binding.btConsultation
            btKonsultasi.setOnClickListener {
                val intent = Intent(this, ScheduleConsultationActivity::class.java)
                intent.putExtra(DOCTOR_ID_EXTRA, doctor.id)
                startActivity(intent)
            }

            val backButton = findViewById(R.id.ivBack) as ImageView
            backButton.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun doctorFromID(doctorID: Int): DoctorModel? {
        for (doctor in doctorList) {
            if (doctor.id == doctorID) return doctor
        }
        return null
    }
}



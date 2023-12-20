package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.ActivityScheduleConsultationBinding
import com.collaboracrew.catwell.model.DateModel
import com.collaboracrew.catwell.model.DoctorModel
import com.collaboracrew.catwell.model.TimeModel
import com.collaboracrew.catwell.viewmodel.DateAdapter
import com.collaboracrew.catwell.viewmodel.TimeAdapter
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Locale

class ScheduleConsultationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleConsultationBinding
    private lateinit var btSchedule: Button
    private var selectedTime: String? = null
    private var selectedDate: String? = null
    private val dateList = ArrayList<DateModel>()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityScheduleConsultationBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        val doctorID = intent.getIntExtra(DOCTOR_ID_EXTRA, -1)
//        val doctor = doctorFromID(doctorID)
//
//        val cover: ImageView = findViewById(R.id.doctorImageView)
//        val name: TextView = findViewById(R.id.tvDoctorName)
//        val instance: TextView = findViewById(R.id.tvInstanceName)
//        val price: TextView = findViewById(R.id.tvPrice)
//
//        if (doctor != null) {
//            cover.setImageResource(doctor)
//            name.text = doctor
//            instance.text = doctor
//            price.text = doctor
//
//            setupDateRecyclerView()
//            setupTimeRecyclerViews()
//            setupScheduleButton()
//        }
//    }
//
//    private fun setupDateRecyclerView() {
//        val dateRecyclerView = binding.dateRecyclerView
//        dateRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//
//        val dateCalendar = Calendar.getInstance()
//        for (i in 0 until 4) {
//            dateList.add(
//                DateModel(
//                    SimpleDateFormat("dd", Locale.getDefault()).format(dateCalendar.time),
//                    SimpleDateFormat("E", Locale.getDefault()).format(dateCalendar.time)
//                )
//            )
//            dateCalendar.add(Calendar.DAY_OF_MONTH, 1)
//        }
//
//        val dateAdapter = DateAdapter(dateList)
//        dateRecyclerView.adapter = dateAdapter
//
//        dateAdapter.setOnItemClickListener(object : DateAdapter.OnItemClickListener {
//            override fun onItemClick(position: Int) {
//                val selectedDate = dateList[position]
//                showToast("Tanggal dipilih: ${selectedDate.date}")
//                updateTotalPriceUI()
//            }
//        })
//    }
//
//    private fun setupTimeRecyclerViews() {
//        val timeRecyclerViewPagi: RecyclerView = findViewById(R.id.timeRecyclerView)
//        timeRecyclerViewPagi.layoutManager = GridLayoutManager(this, 5)
//
//        // Inisialisasi RecyclerView untuk jam konsultasi siang
//        val timeRecyclerViewSiang: RecyclerView = findViewById(R.id.timeRecyclerViewSiang)
//        timeRecyclerViewSiang.layoutManager = GridLayoutManager(this, 5)
//
//        // Inisialisasi RecyclerView untuk jam konsultasi sore
//        val timeRecyclerViewSore: RecyclerView = findViewById(R.id.timeRecyclerViewSore)
//        timeRecyclerViewSore.layoutManager = GridLayoutManager(this, 5)
//
//        // Inisialisasi RecyclerView untuk jam konsultasi malam
//        val timeRecyclerViewMalam: RecyclerView = findViewById(R.id.timeRecyclerViewMalam)
//        timeRecyclerViewMalam.layoutManager = GridLayoutManager(this, 5)
//
//        // Buat daftar waktu konsultasi untuk pagi, siang, dan malam
//        val timeListPagi = mutableListOf<TimeModel>()
//        val timeListSiang = mutableListOf<TimeModel>()
//        val timeListSore = mutableListOf<TimeModel>()
//        val timeListMalam = mutableListOf<TimeModel>()
//
//        val timeCalendar = Calendar.getInstance()
//        timeCalendar.set(Calendar.HOUR_OF_DAY, 8) // Mulai dari jam 08:00
//        timeCalendar.set(Calendar.MINUTE, 0) // Menit diatur ke 0
//
//        val endCalendarPagi = Calendar.getInstance()
//        endCalendarPagi.set(Calendar.HOUR_OF_DAY, 11) // Hingga jam 11:00
//        endCalendarPagi.set(Calendar.MINUTE, 0) // Menit diatur ke 0
//
//        val endCalendarSiang = Calendar.getInstance()
//        endCalendarSiang.set(Calendar.HOUR_OF_DAY, 14) // Hingga jam 15:00
//        endCalendarSiang.set(Calendar.MINUTE, 0) // Menit diatur ke 0
//
//        val endCalendarSore = Calendar.getInstance()
//        endCalendarSore.set(Calendar.HOUR_OF_DAY, 19) // Hingga jam 18:00
//        endCalendarSore.set(Calendar.MINUTE, 0) // Menit diatur ke 0
//
//        val endCalendarMalam = Calendar.getInstance()
//        endCalendarMalam.set(Calendar.HOUR_OF_DAY, 24) // Hingga jam 23:00
//        endCalendarMalam.set(Calendar.MINUTE, 0) // Menit diatur ke 0
//
//
//        while (timeCalendar.before(endCalendarPagi)) {
//            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
//            val startTime = timeFormat.format(timeCalendar.time)
//            timeCalendar.add(Calendar.HOUR_OF_DAY, 1)
//            timeListPagi.add(TimeModel("$startTime"))
//        }
//
//        while (timeCalendar.before(endCalendarSiang)) {
//            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
//            val startTime = timeFormat.format(timeCalendar.time)
//            timeCalendar.add(Calendar.HOUR_OF_DAY, 1)
//            timeListSiang.add(TimeModel("$startTime"))
//        }
//
//        while (timeCalendar.before(endCalendarSore)) {
//            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
//            val startTime = timeFormat.format(timeCalendar.time)
//            timeCalendar.add(Calendar.HOUR_OF_DAY, 1)
//            timeListSore.add(TimeModel("$startTime"))
//        }
//
//        while (timeCalendar.before(endCalendarMalam)) {
//            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
//            val startTime = timeFormat.format(timeCalendar.time)
//            timeCalendar.add(Calendar.HOUR_OF_DAY, 1)
//            timeListMalam.add(TimeModel("$startTime"))
//        }
//
//        // Inisialisasi adapter dan atur RecyclerView untuk waktu pagi, siang, sore, dan malam
//        val timeAdapterPagi = TimeAdapter(timeListPagi)
//        val timeAdapterSiang = TimeAdapter(timeListSiang)
//        val timeAdapterSore = TimeAdapter(timeListSore)
//        val timeAdapterMalam = TimeAdapter(timeListMalam)
//        timeRecyclerViewPagi.adapter = timeAdapterPagi
//
//        timeAdapterPagi.setOnItemClickListener(object : TimeAdapter.OnItemClickListener {
//            override fun onItemClick(position: Int) {
//                val selectedTimePagi = timeListPagi[position].time
//                if (selectedTime != selectedTimePagi) {
//                    selectedTime = selectedTimePagi
//
//                    timeAdapterPagi.setSelectedItemPosition(position)
//
//                    timeAdapterSiang.setSelectedItemPosition(-1)
//                    timeAdapterSore.setSelectedItemPosition(-1)
//                    timeAdapterMalam.setSelectedItemPosition(-1)
//                }
//                updateTotalPriceUI()
//            }
//        })
//
//
//
//        timeRecyclerViewSiang.adapter = timeAdapterSiang
//
//        timeAdapterSiang.setOnItemClickListener(object : TimeAdapter.OnItemClickListener {
//            override fun onItemClick(position: Int) {
//                val selectedTimeSiang = timeListSiang[position].time
//                if (selectedTime != selectedTimeSiang) {
//                    selectedTime = selectedTimeSiang
//
//                    timeAdapterSiang.setSelectedItemPosition(position)
//
//                    timeAdapterPagi.setSelectedItemPosition(-1)
//                    timeAdapterSore.setSelectedItemPosition(-1)
//                    timeAdapterMalam.setSelectedItemPosition(-1)
//                }
//                updateTotalPriceUI()
//            }
//        })
//
//
//        timeRecyclerViewSore.adapter = timeAdapterSore
//
//        timeAdapterSore.setOnItemClickListener(object : TimeAdapter.OnItemClickListener {
//            override fun onItemClick(position: Int) {
//                val selectedTimeSore = timeListSore[position].time
//                if (selectedTime != selectedTimeSore) {
//                    selectedTime = selectedTimeSore
//
//                    timeAdapterSore.setSelectedItemPosition(position)
//
//                    timeAdapterPagi.setSelectedItemPosition(-1)
//                    timeAdapterSiang.setSelectedItemPosition(-1)
//                    timeAdapterMalam.setSelectedItemPosition(-1)
//                }
//                updateTotalPriceUI()
//            }
//        })
//
//
//        timeRecyclerViewMalam.adapter = timeAdapterMalam
//
//        timeAdapterMalam.setOnItemClickListener(object : TimeAdapter.OnItemClickListener {
//            override fun onItemClick(position: Int) {
//                val selectedTimeMalam = timeListMalam[position].time
//                if (selectedTime != selectedTimeMalam) {
//                    selectedTime = selectedTimeMalam
//                    timeAdapterMalam.setSelectedItemPosition(position)
//
//                    timeAdapterPagi.setSelectedItemPosition(-1)
//                    timeAdapterSiang.setSelectedItemPosition(-1)
//                    timeAdapterSore.setSelectedItemPosition(-1)
//                }
//                updateTotalPriceUI()
//            }
//        })
//        // button schedule
//        btSchedule = findViewById(R.id.scheduleButton)
//
//        btSchedule.setOnClickListener {
//            val intent = Intent(this, PaymentActivity::class.java)
//            startActivity(intent)
//        }
//
//        val backButton = findViewById(R.id.ivBack) as ImageView
//
//        backButton.setOnClickListener {
//            onBackPressed()
//        }
//    }
//
//    private fun setupScheduleButton() {
//        val btSchedule = binding.scheduleButton
//        btSchedule.setOnClickListener {
//            val intent = Intent(this, PaymentActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun updateTotalPriceUI() {
//        val consultationPrice = 130_000.0 // Harga konsultasi tetap
//        val totalPrice = consultationPrice
//
//        val totalPriceTextView = findViewById<TextView>(R.id.totalPriceTextView)
//        if (selectedDate != null || selectedTime != null) {
//            totalPriceTextView.text = "Rp$totalPrice"
//        } else {
//            totalPriceTextView.text = "-" // Tampilkan teks kosong jika belum ada tanggal atau waktu yang dipilih
//        }
//    }
//
//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
//
//    private fun doctorFromID(doctorID: Int): DoctorModel?
//    {
//        for(doctor)
//        {
//            if(doctor.id == doctorID)
//                return doctor
//        }
//        return null
//    }
}
package com.collaboracrew.catwell.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.viewmodel.NotifikasiListAdapter

class NotifikasiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifikasi)

        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            onBackPressed()
        }


        val notif  = listOf<String>(
            "Dokter kamu sudah siap, silakan masuk ruang chat. Selamat berkonsultasi!",
            "Apakah kucing Anda mengalami perubahan perilaku? Hubungi segera dokter hewan kami untuk saran medis.",
            "Apakah Anda ingin mengatur janji temu konsultasi dengan dokter hewan? Gunakan fitur kami untuk berkonsultasi tentang kucing Anda.",
            "Penting untuk memberi vaksinasi yang tepat pada kucing Anda. Lihat jadwal vaksinasi dan jangan sampai ketinggalan.",
            "Terima kasih telah mempercayakan kesehatan kucing Anda kepada kami. Kami selalu siap membantu untuk menjaga kucing Anda tetap bahagia dan sehat.",
            "Selamat datang kembali di Catwell! Jangan ragu untuk bertanya jika ada masalah kesehatan atau pertanyaan mengenai kucing kesayangan Anda.",
            "Kami ingin mendengar pendapat Anda! Mohon berikan ulasan tentang pengalaman Anda dengan layanan kami. Ulasan Anda sangat berharga bagi kami."
        )

        val mainAdapter = NotifikasiListAdapter(notif)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = mainAdapter
    }
}
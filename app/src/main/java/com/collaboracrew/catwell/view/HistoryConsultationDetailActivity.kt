package com.collaboracrew.catwell.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceControl.Transaction
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.collaboracrew.catwell.MainActivity
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.HistoryConsultationDetailBinding
import com.collaboracrew.catwell.model.RiwayatModel
import com.collaboracrew.catwell.model.TRANSACTION_ID_EXTRA
import com.collaboracrew.catwell.model.transactionList

class HistoryConsultationDetailActivity : AppCompatActivity() {
    private lateinit var binding: HistoryConsultationDetailBinding
    private lateinit var btNewSchedule: Button
    private lateinit var btConsultationHistory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HistoryConsultationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btNewSchedule = findViewById(R.id.bt_newSchedule)
        btConsultationHistory = findViewById(R.id.bt_consultationHistory)

        val transactionID = intent.getIntExtra(TRANSACTION_ID_EXTRA, -1)
        val transaction = transactionFromID(transactionID)


        if (transaction != null) {
            binding.name.text = transaction.doctor
            binding.instance.text = transaction.instance
            binding.date.text = transaction.date
            binding.time.text = transaction.time
            binding.price.text = transaction.price.toString()
            binding.ratingBar.rating = transaction.rating
        }

        btNewSchedule.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("newSchedule", true) // Sinyal untuk kembali ke beranda
            startActivity(intent)
        }

        btConsultationHistory.setOnClickListener {
            val intent = Intent(this, ChatLogActivity::class.java)
            startActivity(intent)
        }

        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("transactionData", true)
            startActivity(intent)
        }

    }

    private fun transactionFromID(transactionID: Int): RiwayatModel? {
        for (transaction in transactionList) {
            if (transaction.id == transactionID) return transaction
        }
        return null
    }
}

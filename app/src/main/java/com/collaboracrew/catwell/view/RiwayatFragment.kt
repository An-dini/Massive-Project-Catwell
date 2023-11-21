package com.collaboracrew.catwell.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.collaboracrew.catwell.MainActivity
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.viewmodel.RiwayatAdapter.TransactionClickListener
import com.collaboracrew.catwell.databinding.FragmentRiwayatBinding
import com.collaboracrew.catwell.model.RiwayatModel
import com.collaboracrew.catwell.model.TRANSACTION_ID_EXTRA
import com.collaboracrew.catwell.viewmodel.RiwayatAdapter

class RiwayatFragment : Fragment(), TransactionClickListener {
    private lateinit var binding: FragmentRiwayatBinding
    private val transactionList = mutableListOf<RiwayatModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactions()
        setupRecyclerView()
    }

    override fun onClick(transaction: RiwayatModel) {
        val intent = Intent(requireContext(), HistoryConsultationDetailActivity::class.java)
        intent.putExtra(TRANSACTION_ID_EXTRA, transaction.id)
        startActivity(intent)
    }



    private fun transactions(){
        val doctorName = resources.getStringArray(R.array.doctor_name_trans)
        val doctorInstance = resources.getStringArray(R.array.doctor_instance_trans)
        val priceTrans = 130000
        val ratingTrans = resources.getStringArray(R.array.rating_trans)
        val dateTrans = resources.getStringArray(R.array.date_trans)
        val timeTrans = resources.getStringArray(R.array.time_trans)
        val paymentMethod = resources.getStringArray(R.array.payment_trans)

        for (i in doctorName.indices) {
            val doctor = doctorName[i]
            val transaction = RiwayatModel(
                coverResource(doctor),
                doctor,
                doctorInstance[i],
                ratingTrans[i].toFloat(),
                dateTrans[i],
                timeTrans[i],
                priceTrans,
                paymentMethod[i],
                id = transactionList.size
            )
            transactionList.add(transaction)
        }
    }

    private fun coverResource(doctor: String): Int {
        return when (doctor) {
            "Drh. Aji Kusuma" -> R.drawable.aji
            "Drh. Mutiara" -> R.drawable.mutiara
            "Drh. Chandra" -> R.drawable.chandra
            "Drh. Nadine" -> R.drawable.nadine
            "Drh. Caroline" -> R.drawable.caroline
            "Drh. Julia" -> R.drawable.julia
            "Drh. Aisha" -> R.drawable.aisha
            "Drh. Nalend" -> R.drawable.nalend
            "Drh. Isa" -> R.drawable.lisa
            "Drh. Annisa" -> R.drawable.annisa
            "Drh. Nabila" -> R.drawable.nabila
            "Drh. Dion" -> R.drawable.dion
            else -> R.drawable.aji
        }
    }

    private fun setupRecyclerView() {
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RiwayatAdapter(transactionList, this@RiwayatFragment)
        }
    }
}

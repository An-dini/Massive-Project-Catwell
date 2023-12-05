package com.collaboracrew.catwell.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.FragmentKonsultasiDokterBinding
import com.collaboracrew.catwell.model.CHAT_PATIENT_HISTORY_ID_EXTRA
import com.collaboracrew.catwell.model.ChatPatientHistoryModel
import com.collaboracrew.catwell.viewmodel.RiwayatChatAdapter

class KonsultasiDokterFragment : Fragment(), RiwayatChatAdapter.ChatClickListener {
    private lateinit var binding: FragmentKonsultasiDokterBinding
    private val chatPatientHistoryList = mutableListOf<ChatPatientHistoryModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKonsultasiDokterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactions()

        val openChatData = chatPatientHistoryList.subList(0, 2)
        val closedChatData = chatPatientHistoryList.subList(2, chatPatientHistoryList.size)

        binding.rvOpenChat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RiwayatChatAdapter(openChatData, this@KonsultasiDokterFragment)
        }

        binding.rvClosedChat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = RiwayatChatAdapter(closedChatData, this@KonsultasiDokterFragment)
        }
    }

    override fun onClick(transaction: ChatPatientHistoryModel) {
        val intent = Intent(requireContext(), ChatLogDoctorActivity::class.java)
        intent.putExtra(CHAT_PATIENT_HISTORY_ID_EXTRA, transaction.id)
        startActivity(intent)
    }



    private fun transactions(){
        val patientName = resources.getStringArray(R.array.patient_name_trans)
        val isiChat = resources.getStringArray(R.array.isi_chat_trans)

        for (i in patientName.indices) {
            val user = patientName[i]
            val transaction = ChatPatientHistoryModel(
                coverResource(user),
                user,
                isiChat[i],
                id = chatPatientHistoryList.size
            )
            chatPatientHistoryList.add(transaction)
        }
    }

    private fun coverResource(user: String): Int {
        return when (user) {
            "Budi" -> R.drawable.aji
            "Larasati" -> R.drawable.mutiara
            "Joko" -> R.drawable.chandra
            "Eka" -> R.drawable.nadine
            "Saras" -> R.drawable.caroline
            "Intan" -> R.drawable.julia
            else -> R.drawable.aji
        }
    }

}

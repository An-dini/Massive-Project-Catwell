package com.collaboracrew.catwell.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.PatientHistoryModel
import com.collaboracrew.catwell.viewmodel.PatientListAdapter

class FragmentBeranda_dk : Fragment() {
    private lateinit var rvBerandaDr: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val baseKonsul = view.findViewById<RelativeLayout>(R.id.baseKonsul)
        val illustration = view.findViewById<ImageView>(R.id.imageView3)

        val btReject = view.findViewById<Button>(R.id.btn_reject)
        btReject.setOnClickListener {
            baseKonsul.visibility = View.GONE
            illustration.visibility = View.VISIBLE
        }
        val btAccept = view.findViewById<Button>(R.id.btn_accept)
        btAccept.setOnClickListener {
            pilihanDialog()
        }


        rvBerandaDr = view.findViewById(R.id.rvBerandaDr)
        rvBerandaDr.layoutManager = LinearLayoutManager(requireContext())

        rvBerandaDr.adapter = PatientListAdapter(getSampleScheduleData(), clickListener = {
            pilihanDialog()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beranda_dk, container, false)
    }

    private fun getSampleScheduleData(): List<PatientHistoryModel> {
        return listOf(
            PatientHistoryModel(R.drawable.usereditprofil, "Shafa Shaira", "Rabu", "06 Desember 2023","19:30 - 20:30", "OVO", "130.000", "-", "-", "Chat", "-", "-"),
            PatientHistoryModel(R.drawable.usereditprofil, "Budi", "Jumat", "08 Desember 2023","09:30 - 10:30", "OVO", "130.000", "-", "-", "Chat", "-", "-"),
            PatientHistoryModel(R.drawable.usereditprofil, "Larasati", "Senin", "11 Desember 2023","09:30 - 10:30", "OVO", "130.000", "-", "-", "Chat", "-", "-")
        )
    }

    private  fun  pilihanDialog(){
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_pilihan_konsul)

        val btYa: ImageButton = dialog.findViewById(R.id.btChat)
        btYa.setOnClickListener {
            val intent = Intent(requireContext(), ChatLogDoctorActivity::class.java)
            intent.putExtra("chatBaru", true)
            startActivity(intent)
        }

        val btTidak: ImageButton = dialog.findViewById(R.id.btVidcall)
        btTidak.setOnClickListener {
            val intent = Intent(requireContext(), Room_Vidcall::class.java)
            startActivity(intent)
        }

        dialog.show()
    }
}
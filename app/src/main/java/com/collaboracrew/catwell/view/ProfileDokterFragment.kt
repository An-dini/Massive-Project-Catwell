package com.collaboracrew.catwell.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.collaboracrew.catwell.R
import kotlin.math.log


class ProfileDokterFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("CatWellPref", Context.MODE_PRIVATE)

        val edtprofil_dk = view.findViewById<TextView>(R.id.editProfile_dk)
        edtprofil_dk.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileDokter::class.java))
        }
        val edtpasswd_dk = view.findViewById<TextView>(R.id.edtPass_dk)
        edtpasswd_dk.setOnClickListener {
            startActivity(Intent(requireContext(), EditPasswordDokter::class.java))
        }
        val ttgapk_dk = view.findViewById<TextView>(R.id.ttgapk_dk)
        ttgapk_dk.setOnClickListener {
            startActivity(Intent(requireContext(), TentangAplikasi::class.java))
        }
        val keluar_dk = view.findViewById<TextView>(R.id.keluar_dk)
        keluar_dk.setOnClickListener {
            LogoutAccountDialog()
        }
        val hpsakun_dk = view.findViewById<TextView>(R.id.hps_akn_dk)
        hpsakun_dk.setOnClickListener {
            deleteAccountDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_dokter, container, false)
    }

    private fun deleteAccountDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_hapus_akun)

        val btYa: Button = dialog.findViewById(R.id.btYa)
        btYa.setOnClickListener(View.OnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("isDoctorLoggedIn")
            editor.apply()

            val intent = Intent(requireContext(), Pilih_Sebagai::class.java)
            startActivity(intent)
            requireActivity().finish()
        })

        val btTidak: Button = dialog.findViewById(R.id.btTidak)
        btTidak.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun LogoutAccountDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_logout)

        val btYa: Button = dialog.findViewById(R.id.btYa)
        btYa.setOnClickListener(View.OnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("isDoctorLoggedIn")
            editor.apply()

            val intent = Intent(requireContext(), Pilih_Sebagai::class.java)
            startActivity(intent)
            requireActivity().finish()
        })

        val btTidak: Button = dialog.findViewById(R.id.btTidak)
        btTidak.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
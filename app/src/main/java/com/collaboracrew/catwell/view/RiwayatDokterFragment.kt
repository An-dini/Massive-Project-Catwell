package com.collaboracrew.catwell.view

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.ActivityRiwayatPasienBinding
import com.collaboracrew.catwell.databinding.FragmentInfoVetBinding
import com.collaboracrew.catwell.databinding.FragmentKonsultasiDokterBinding

class RiwayatDokterFragment : Fragment() {
    private lateinit var binding: ActivityRiwayatPasienBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityRiwayatPasienBinding.inflate(inflater, container, false)


        if (savedInstanceState == null) {
            replaceFragment(AcceptConsultationFragment())
        }

        binding.btAccept.setOnClickListener {
            replaceFragment(AcceptConsultationFragment())

            binding.btAccept.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))
            binding.btAccept.setTextAppearance(R.style.poppinsSemiBold16spOrange)

            binding.btReject.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.PrimaryColor))
            binding.btReject.setTextAppearance(R.style.poppinsSemiBold16spWhite)
        }
        binding.btReject.setOnClickListener {
            replaceFragment(RejectConsultationFragment())

            binding.btAccept.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.PrimaryColor))
            binding.btAccept.setTextAppearance(R.style.poppinsSemiBold16spWhite)

            binding.btReject.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))
            binding.btReject.setTextAppearance(R.style.poppinsSemiBold16spOrange)
        }

        return binding.root
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}
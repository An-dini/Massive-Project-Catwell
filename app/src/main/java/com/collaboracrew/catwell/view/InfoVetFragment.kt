package com.collaboracrew.catwell.view

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.FragmentInfoVetBinding

class InfoVetFragment : Fragment() {
    private lateinit var binding: FragmentInfoVetBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoVetBinding.inflate(inflater, container, false)
        val view = binding.root

        if (savedInstanceState == null) {
            replaceFragment(VetFragment())
        }

        binding.btnVet.setOnClickListener {
            replaceFragment(VetFragment())

            binding.btnVet.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))
            binding.btnVet.setTextAppearance(R.style.poppinsSemiBold16spOrange)

            binding.btnPsw.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.PrimaryColor))
            binding.btnPsw.setTextAppearance(R.style.poppinsSemiBold16spWhite)
        }
        binding.btnPsw.setOnClickListener {
            replaceFragment(PuskeswanFragment())

            binding.btnVet.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.PrimaryColor))
            binding.btnVet.setTextAppearance(R.style.poppinsSemiBold16spWhite)

            binding.btnPsw.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))
            binding.btnPsw.setTextAppearance(R.style.poppinsSemiBold16spOrange)
        }

        return view
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_info, fragment)
        fragmentTransaction.commit()
    }
}
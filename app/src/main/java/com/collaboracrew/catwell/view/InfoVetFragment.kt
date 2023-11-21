package com.collaboracrew.catwell.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.FragmentInfoVetBinding

class InfoVetFragment : Fragment() {
    private lateinit var binding: FragmentInfoVetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoVetBinding.inflate(inflater, container, false)
        val view = binding.root

        if (savedInstanceState == null) {
            replaceFragment(VetFragment())
        }

        binding.btnVet.setOnClickListener { replaceFragment(VetFragment()) }
        binding.btnPsw.setOnClickListener { replaceFragment(PuskeswanFragment()) }

        return view
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_info, fragment)
        fragmentTransaction.commit()
    }
}
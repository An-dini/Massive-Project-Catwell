package com.collaboracrew.catwell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.collaboracrew.catwell.view.DiskusiFragment
import com.collaboracrew.catwell.view.FragmentBeranda_dk
import com.collaboracrew.catwell.view.KonsultasiDokterFragment
import com.collaboracrew.catwell.view.ProfileDokterFragment
import com.collaboracrew.catwell.view.RiwayatDokterFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DoctorMainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_doctor)

        bottomNavigationView = findViewById(R.id.bottomNavigationBarDoctor)

        if (intent.getBooleanExtra("simpanProfil", false)) {
            replaceFragment(ProfileDokterFragment())
            bottomNavigationView.selectedItemId = R.id.profileDokter
        } else if (intent.getBooleanExtra("listChatDoctor", false)) {
            replaceFragment(KonsultasiDokterFragment())
            bottomNavigationView.selectedItemId = R.id.konsultasiDokter
        }else if (intent.getBooleanExtra("listDiskusi", false)) {
            replaceFragment(DiskusiFragment())
            bottomNavigationView.selectedItemId = R.id.diskusiDokter
        } else {
            replaceFragment(FragmentBeranda_dk())
            bottomNavigationView.selectedItemId = R.id.berandaDokter
        }

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.berandaDokter -> {
                    replaceFragment(FragmentBeranda_dk())
                    true
                }
                R.id.diskusiDokter -> {
                    replaceFragment(DiskusiFragment())
                    true
                }
                R.id.konsultasiDokter -> {
                    replaceFragment(KonsultasiDokterFragment())
                    true
                }
                R.id.riwayatDokter -> {
                    replaceFragment(RiwayatDokterFragment())
                    true
                }
                R.id.profileDokter -> {
                    replaceFragment(ProfileDokterFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
        Log.d("MyApp", "Replacing fragment with ${fragment.javaClass.simpleName}")
    }
}

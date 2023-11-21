package com.collaboracrew.catwell

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.collaboracrew.catwell.view.BerandaFragment
import com.collaboracrew.catwell.view.DiskusiFragment
import com.collaboracrew.catwell.view.InfoVetFragment
import com.collaboracrew.catwell.view.KonsultasiFragment
import com.collaboracrew.catwell.view.RiwayatFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationBar)

        if (intent.getBooleanExtra("transactionData", false)) {
            replaceFragment(RiwayatFragment())
            bottomNavigationView.selectedItemId = R.id.riwayat
        } else if (intent.getBooleanExtra("newSchedule", false)) {
            replaceFragment(KonsultasiFragment())
            bottomNavigationView.selectedItemId = R.id.konsultasi
        } else {
            replaceFragment(BerandaFragment())
            bottomNavigationView.selectedItemId = R.id.beranda
        }

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.beranda -> {
                    replaceFragment(BerandaFragment())
                    true
                }
                R.id.diskusi -> {
                    replaceFragment(DiskusiFragment())
                    true
                }
                R.id.konsultasi -> {
                    replaceFragment(KonsultasiFragment())
                    true
                }
                R.id.riwayat -> {
                    replaceFragment(RiwayatFragment())
                    true
                }
                R.id.infoVet -> {
                    replaceFragment(InfoVetFragment())
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

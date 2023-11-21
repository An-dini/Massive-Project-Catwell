package com.collaboracrew.catwell.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.databinding.ActivityEditAddressBinding
import com.collaboracrew.catwell.model.Address
import com.collaboracrew.catwell.model.addressList
import com.collaboracrew.catwell.view.ProfileActivity

class EditAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addressId = intent.getIntExtra("addressId", -1)
        val address = addressFromID(addressId)

        if (address != null) {
            binding.etName.text = Editable.Factory.getInstance().newEditable(address.name)
            binding.etPhone.text = Editable.Factory.getInstance().newEditable(address.phone)
            binding.etProvince.text = Editable.Factory.getInstance().newEditable(address.province)
            binding.etCity.text = Editable.Factory.getInstance().newEditable(address.city)
            binding.etStreet.text = Editable.Factory.getInstance().newEditable(address.street)
            binding.switchDefault.isChecked = address.settingDefault
        }

        val btnSimpan = findViewById(R.id.btnSimpan) as Button
        btnSimpan.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val backButton = findViewById(R.id.ivBack) as ImageView
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun addressFromID(addressId: Int): Address? {
        for (address in addressList) {
            if (address.id == addressId) return address
        }
        return null
    }
}

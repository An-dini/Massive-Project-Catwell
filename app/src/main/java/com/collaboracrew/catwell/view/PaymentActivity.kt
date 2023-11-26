package com.collaboracrew.catwell.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.MainActivity
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.PaymentMethodModel
import com.collaboracrew.catwell.viewmodel.PaymentMethodsAdapter

class PaymentActivity : AppCompatActivity() {

    private lateinit var paymentMethodsAdapter: PaymentMethodsAdapter
    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val paymentMethodsRecyclerView: RecyclerView = findViewById(R.id.rvPaymentMethod)

        // Create the list of PaymentMethodModel items
        val paymentMethodsList = listOf(
            PaymentMethodModel(R.drawable.ic_dana_logo,"DANA"),
            PaymentMethodModel(R.drawable.ic_ovo_logo, "OVO"),
            PaymentMethodModel(R.drawable.ic_gopay_logo, "GOPAY"),
            PaymentMethodModel(R.drawable.ic_shopeepay_logo, "ShopeePay"),
            PaymentMethodModel(R.drawable.ic_linkaja_logo, "LinkAja"),
            PaymentMethodModel(R.drawable.ic_mandiri_logo, "Mandiri"),
            PaymentMethodModel(R.drawable.ic_bca_logo, "BCA"),
            PaymentMethodModel(R.drawable.ic_bri_logo, "BRI"),
            PaymentMethodModel(R.drawable.ic_bni_logo, "BNI")
        )

        // Create and set the adapter for the RecyclerView
        paymentMethodsAdapter = PaymentMethodsAdapter(paymentMethodsList)
        paymentMethodsAdapter.setPaymentNameTextView(findViewById(R.id.paymentName)) // Inisialisasi paymentNameTextView
        paymentMethodsRecyclerView.adapter = paymentMethodsAdapter

        // Use a GridLayoutManager for a grid layout
        paymentMethodsRecyclerView.layoutManager = GridLayoutManager(this, 5)

        val viewMoreButton: Button = findViewById(R.id.viewMoreButton)

        viewMoreButton.setOnClickListener {
            isExpanded = !isExpanded
            paymentMethodsAdapter.expandList()
            if (isExpanded) {
                viewMoreButton.text = "Sembunyikan"
            } else {
                viewMoreButton.text = "Lainnya"
            }
        }

        val btpay = findViewById(R.id.btPay) as Button
        btpay.setOnClickListener {
            val intent = Intent(this, PembayaranChat::class.java)
            startActivity(intent)
        }

        val backButton = findViewById(R.id.ivBack) as ImageView

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
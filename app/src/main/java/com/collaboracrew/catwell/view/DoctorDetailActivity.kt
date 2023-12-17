package com.collaboracrew.catwell.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.model.DoctorModel
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme
import com.midtrans.sdk.corekit.models.BillingAddress
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.corekit.models.ItemDetails
import com.midtrans.sdk.corekit.models.ShippingAddress
import com.midtrans.sdk.uikit.SdkUIFlowBuilder
import com.squareup.picasso.Picasso

class DoctorDetailActivity : AppCompatActivity() {
    private val doctor by lazy { intent.getSerializableExtra("dokter") as DoctorModel.Data }

    private lateinit var bt_consultation: Button
    private lateinit var bt_consultation2: Button
    private lateinit var ivImageDokter: ImageView
    private lateinit var tvDokterNama: TextView
    private lateinit var tvInstanceVet: TextView
    private lateinit var schedule: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_detail)

        setupView ()
        setupListener()

    }

    private fun setupView (){
        bt_consultation = findViewById(R.id.bt_consultation)
        bt_consultation2 = findViewById(R.id.bt_consultation2)
        ivImageDokter = findViewById(R.id.ivImageDokter)
        tvDokterNama = findViewById(R.id.tvDokterNama)
        tvInstanceVet = findViewById(R.id.tvInstanceVet)
        schedule = findViewById(R.id.schedule)

        Picasso.get().load(doctor.foto_dokter).into(ivImageDokter)

        tvDokterNama.setText(doctor.nama_dokter)
        tvInstanceVet.setText(doctor.tpt_praktek)
        schedule.setText("Senin - Jumat . 08.00 - 17.00")

        SdkUIFlowBuilder.init()
            .setClientKey("SB-Mid-client-J965Pxy-mc1iojjc")
            .setContext(applicationContext)
            .setTransactionFinishedCallback (TransactionFinishedCallback {
              result ->
//              logic
            })
            .setMerchantBaseUrl("https://collaboracrew.000webhostapp.com/payment_response.php/")
            .enableLog(true)
            .setColorTheme(CustomColorTheme("#F28705","#3F3D56","#F28705"))
            .setLanguage("id")
            .buildSDK()

    }

    private fun setupListener() {
        bt_consultation.setOnClickListener {
            val hargaKonsultasi = "130000"
            val jumlahKonsultasi = "1"
            val totalHarga = hargaKonsultasi.toDouble() * jumlahKonsultasi.toInt()

            val transactionRequest = TransactionRequest("Catwell-"+System.currentTimeMillis().toShort() + "", totalHarga)
            val detail = ItemDetails("NamaItem", hargaKonsultasi.toDouble(), jumlahKonsultasi.toInt(), "Konsultasi via chat")
            val itemDetails = arrayListOf<ItemDetails>()
            itemDetails.add(detail)
            uiKitDetail(transactionRequest)
            transactionRequest.itemDetails = itemDetails

            MidtransSDK.getInstance().transactionRequest = transactionRequest
            MidtransSDK.getInstance().startPaymentUiFlow(this)
        }

        bt_consultation2.setOnClickListener {
            val hargaKonsultasi = "150000"
            val jumlahKonsultasi = "1"
            val totalHarga = hargaKonsultasi.toDouble() * jumlahKonsultasi.toInt()

            val transactionRequest = TransactionRequest("Catwell"+System.currentTimeMillis().toShort() + "", totalHarga)
            val detail = ItemDetails("NamaItem", hargaKonsultasi.toDouble(), jumlahKonsultasi.toInt(), "Konsultasi via video call")
            val itemDetails = arrayListOf<ItemDetails>()
            itemDetails.add(detail)
            uiKitDetail(transactionRequest)
            transactionRequest.itemDetails = itemDetails

            MidtransSDK.getInstance().transactionRequest = transactionRequest
            MidtransSDK.getInstance().startPaymentUiFlow(this)
        }
    }

    private fun uiKitDetail(transactionRequest: TransactionRequest) {
        val customerDetails = CustomerDetails()
        customerDetails.customerIdentifier = "jajal"
        customerDetails.phone = "08123987827"
        customerDetails.email = "jajal@gmail.com"
        customerDetails.firstName = "jajal"
        customerDetails.lastName = "jajal"
        val shippingAddress = ShippingAddress()
        shippingAddress.address = "Jakarta, Indonesia"
        shippingAddress.city = "Jakarta"
        shippingAddress.postalCode = "16710"
        customerDetails.shippingAddress = shippingAddress
        val billingAddress = BillingAddress()
        billingAddress.address = "Jakarta, Indonesia"
        billingAddress.city = "Jakarta"
        billingAddress.postalCode = "16710"
        customerDetails.billingAddress = billingAddress

        transactionRequest.customerDetails = customerDetails

    }

}
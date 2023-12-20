package com.collaboracrew.catwell.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.collaboracrew.catwell.R
import com.collaboracrew.catwell.api.ApiRetrofit
import com.collaboracrew.catwell.model.DoctorModel
import com.collaboracrew.catwell.model.TransactionModel
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoctorDetailActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private val doctor by lazy { intent.getSerializableExtra("dokter") as DoctorModel.Data }

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var bt_consultation: Button
    private lateinit var bt_consultation2: Button
    private lateinit var ivImageDokter: ImageView
    private lateinit var tvDokterNama: TextView
    private lateinit var tvInstanceVet: TextView
    private lateinit var schedule: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_detail)

        val ivBack = findViewById<ImageView>(R.id.ivBack)
        ivBack.setOnClickListener {
            onBackPressed()
        }

        sharedPreferences = getSharedPreferences("CatWellPref", Context.MODE_PRIVATE)

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
            .setTransactionFinishedCallback(TransactionFinishedCallback { result ->

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

            val tipe_konsultasi = "Chat"
            val ID_Dokter = doctor.id_dokter
            Log.e("id dokter cek", "id_dokter : $ID_Dokter")
            val Email_User = sharedPreferences.getString("Email_User", "") ?: ""
            Log.e("email user cek", "email_user : $Email_User")
            val order_id = "Catwell"+System.currentTimeMillis().toShort() + ""


            val transactionRequest = TransactionRequest(order_id, totalHarga)
            val detail = ItemDetails("NamaItem", hargaKonsultasi.toDouble(), jumlahKonsultasi.toInt(), "chat")
            val itemDetails = arrayListOf<ItemDetails>()
            itemDetails.add(detail)
            uiKitDetail(transactionRequest)
            transactionRequest.itemDetails = itemDetails

            MidtransSDK.getInstance().transactionRequest = transactionRequest
            MidtransSDK.getInstance().startPaymentUiFlow(this)

            getTransaction(order_id, Email_User, ID_Dokter, tipe_konsultasi)

        }

        bt_consultation2.setOnClickListener {
            val hargaKonsultasi = "150000"
            val jumlahKonsultasi = "1"
            val totalHarga = hargaKonsultasi.toDouble() * jumlahKonsultasi.toInt()


            val tipe_konsultasi = "Video Call"
            val ID_Dokter = doctor.id_dokter
            Log.e("id dokter cek", "id_dokter : $ID_Dokter")
            val Email_User = sharedPreferences.getString("Email_User", "") ?: ""
            Log.e("email user cek", "email_user : $Email_User")
            val order_id = "Catwell"+System.currentTimeMillis().toShort() + ""


            val transactionRequest = TransactionRequest(order_id, totalHarga)
            val detail = ItemDetails("NamaItem", hargaKonsultasi.toDouble(), jumlahKonsultasi.toInt(), "video call")
            val itemDetails = arrayListOf<ItemDetails>()
            itemDetails.add(detail)
            uiKitDetail(transactionRequest)
            transactionRequest.itemDetails = itemDetails

            MidtransSDK.getInstance().transactionRequest = transactionRequest
            MidtransSDK.getInstance().startPaymentUiFlow(this)

            getTransaction(order_id, Email_User, ID_Dokter, tipe_konsultasi)
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

    private fun getTransaction(order_id: String, Email_User: String, ID_Dokter: String, tipe_konsultasi: String) {
        api.inputPembayaran(order_id, Email_User, ID_Dokter, tipe_konsultasi)
            .enqueue(object : Callback<TransactionModel> {
                override fun onFailure(call: Call<TransactionModel>, t: Throwable) {
                    Log.e("Transaksi Gagal", t.toString())
                }

                override fun onResponse(call: Call<TransactionModel>, response: Response<TransactionModel>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Log.e("Transaksi Berhasil", responseBody.toString())
                        } else {
                            Log.e("Transaksi Gagal", "Response body kosong")
                        }
                    } else {
                        Log.e("Transaksi Gagal", "Kode Error: ${response.code()}")
                    }
                }
            })
    }


}
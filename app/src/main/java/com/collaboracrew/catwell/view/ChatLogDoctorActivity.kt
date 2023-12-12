package com.collaboracrew.catwell.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.DoctorMainActivity
import com.collaboracrew.catwell.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class ChatLogDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log_doctor)

        setupDummyData()

        val backButton = findViewById(R.id.ivBack) as ImageView
        backButton.setOnClickListener {
            onBackPressed()
        }

        var tvTimer = findViewById<TextView>(R.id.tvTimer)
        var ivTimer = findViewById<ImageView>(R.id.ivTimer)
        val handler = Handler(Looper.getMainLooper())

        if (intent.getBooleanExtra("chatBaru", false)) {
            handler.postDelayed({
                timesUpDocDialog()
            }, 3000)
        }else{
            tvTimer.visibility = View.GONE
            ivTimer.visibility = View.GONE
        }

    }

    private fun setupDummyData(){
        val rvChatLog = findViewById<RecyclerView>(R.id.rvChatLog)
        val adapter = GroupAdapter<GroupieViewHolder>()

        rvChatLog.layoutManager = LinearLayoutManager(this) // Menambahkan layout manager jika belum ada
        rvChatLog.adapter = adapter
    }

    private  fun  timesUpDocDialog(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_times_up_doctor)

        val btTutup: Button = dialog.findViewById(R.id.btTutup)
        btTutup.setOnClickListener {
            val intent = Intent(this, DoctorMainActivity::class.java)
            intent.putExtra("listChatDoctor", true)
            startActivity(intent)
        }
        dialog.show()
    }
}

class ChatToDoctor(val text: String) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val textviewFromRow = viewHolder.itemView.findViewById<TextView>(R.id.tvFromRow)
        textviewFromRow.text = text
    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }
}

class ChatFromDoctor(val text: String) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val textviewToRow = viewHolder.itemView.findViewById<TextView>(R.id.tvToRow)
        textviewToRow.text = text
    }

    override fun getLayout(): Int {
        return R.layout.chat_to_row
    }
}



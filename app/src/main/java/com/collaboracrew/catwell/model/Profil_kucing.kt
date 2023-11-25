package com.collaboracrew.catwell.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.collaboracrew.catwell.R

class profil_kucing : AppCompatActivity() {
    private lateinit var  recyclerView: RecyclerView
    private  lateinit var  dataList: ArrayList<DataProfilKucing>
    lateinit var ProfilImage:Array<Int>
    lateinit var ProfilName:Array<String>
    lateinit var BtnDelete:Array<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_kucing)

        ProfilImage = arrayOf(
            R.drawable.userprofile2,
            R.drawable.userprofile3,
            R.drawable.userprofile4,
            R.drawable.userprofile5)
        ProfilName = arrayOf(
            "Kocheng oyen",
            "Si imut",
            "Black cat",
            "Moezaa")
        BtnDelete = arrayOf(
            R.drawable.ic_delete,
            R.drawable.ic_delete,
            R.drawable.ic_delete,
            R.drawable.ic_delete,
        )
        recyclerView=findViewById(R.id.recycleview_ProfilKucing)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataProfilKucing>()
        getData()
    }
    private fun getData(){
        for (i in ProfilImage.indices){
            val dataProfilKucing = DataProfilKucing(ProfilImage[i],ProfilName[i],BtnDelete[i])
            dataList.add(dataProfilKucing)
        }
        recyclerView.adapter = AdapterProfilKucing(dataList)
    }
}
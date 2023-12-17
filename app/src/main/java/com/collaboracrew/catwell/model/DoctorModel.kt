package com.collaboracrew.catwell.model

import java.io.Serializable

val DOCTOR_ID_EXTRA = "doctorExtra"

class DoctorModel(
    val dokter: List<Data>
){
    data class Data(val id_dokter: String,
                    val nama_dokter: String?,
                    val email_dokter: String?,
                    val PW_Dokter: String?,
                    val No_Dokter: String?,
                    val Jenis_Kelamin: String?,
                    val foto_dokter: String?,
                    val No_Registrasi: String?,
                    val Asal_Univ: String?,
                    val No_Praktek: String?,
                    val Spesialisasi: String?,
                    val tpt_praktek: String?,

        ) : Serializable
}

package com.collaboracrew.catwell.model

var patientHistoryList = mutableListOf<PatientHistoryModel>()

val PATIENT_HISTORY_ID_EXTRA = "patient_history_id_extra"

data class PatientHistoryModel(
    val imageProfile: Int,
    val patientName: String,
    val day: String,
    val date: String,
    val time: String,
    val payment: String,
    val price: String,
    val complaint: String,
    val solution: String,
    val method: String,
    val recipe: String,
    val reference: String,
    val id: Int? = patientHistoryList.size
)

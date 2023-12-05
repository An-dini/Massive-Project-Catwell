package com.collaboracrew.catwell.model

var chatPatientHistoryList = mutableListOf<ChatPatientHistoryModel>()

val CHAT_PATIENT_HISTORY_ID_EXTRA = "chat_patient_history_id_extra"

data class ChatPatientHistoryModel(
    val imageProfile: Int,
    val patientName: String,
    val isiChat: String,
    val id: Int? = patientHistoryList.size
)

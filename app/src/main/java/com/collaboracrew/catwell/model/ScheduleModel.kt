package com.collaboracrew.catwell.model

var scheduleList = mutableListOf<ScheduleModel>()

val SCHEDULE_ID_EXTRA = "scheduleExtra"

data class ScheduleModel(
    val imageDoctor: Int,
    val date: String,
    val time: String,
    val doctorName: String,
    val doctorInstance: String,
    val id: Int
)

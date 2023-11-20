package com.collaboracrew.catwell.model

data class UpComingScheduleItem(
    val status: String,
    val doctorImage: Int,
    val doctorName: String,
    val vetName: String,
    val date: String,
    val time: String
)

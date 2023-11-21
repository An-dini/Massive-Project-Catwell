package com.collaboracrew.catwell.model

var doctorList = mutableListOf<DoctorModel>()

val DOCTOR_ID_EXTRA = "doctorExtra"

class DoctorModel(
    var photo: Int,
    var name: String,
    var instance: String,
    var price: String,
    var schedule: String,
    var duration: String,
    var rating: Float,
    val id: Int? = doctorList.size
)

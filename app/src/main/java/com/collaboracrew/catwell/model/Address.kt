package com.collaboracrew.catwell.model

var addressList = mutableListOf<Address>()

val ADDRESS_ID_EXTRA = "addressExtra"
data class Address(
    val place: String,
    val name: String,
    val phone: String,
    val province: String,
    val city: String,
    val street: String,
    val settingDefault: Boolean,
    val id: Int? = addressList.size
)

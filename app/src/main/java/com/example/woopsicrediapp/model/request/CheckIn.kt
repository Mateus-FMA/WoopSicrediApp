package com.example.woopsicrediapp.model.request

import com.google.gson.annotations.SerializedName

data class CheckIn(
    @SerializedName("eventId")
    val eventId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String
)
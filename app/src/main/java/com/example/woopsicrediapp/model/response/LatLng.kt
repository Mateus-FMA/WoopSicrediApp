package com.example.woopsicrediapp.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class LatLng(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(lat)
        parcel.writeDouble(lng)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LatLng> {
        override fun createFromParcel(parcel: Parcel): LatLng {
            return LatLng(parcel)
        }

        override fun newArray(size: Int): Array<LatLng?> {
            return arrayOfNulls(size)
        }
    }
}
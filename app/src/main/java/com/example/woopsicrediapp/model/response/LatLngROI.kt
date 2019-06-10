package com.example.woopsicrediapp.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class LatLngROI(
    @SerializedName("northeast")
    val ne: LatLng?,
    @SerializedName("southwest")
    val sw: LatLng?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(LatLng::class.java.classLoader),
        parcel.readParcelable(LatLng::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(ne, flags)
        parcel.writeParcelable(sw, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LatLngROI> {
        override fun createFromParcel(parcel: Parcel): LatLngROI {
            return LatLngROI(parcel)
        }

        override fun newArray(size: Int): Array<LatLngROI?> {
            return arrayOfNulls(size)
        }
    }
}
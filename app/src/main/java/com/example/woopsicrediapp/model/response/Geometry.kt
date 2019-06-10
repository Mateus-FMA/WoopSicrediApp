package com.example.woopsicrediapp.model.response

import android.os.Parcel
import android.os.Parcelable

data class Geometry(
    val location: LatLng?,
    val type: String?,
    val viewport: LatLngROI?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(LatLng::class.java.classLoader),
        parcel.readString(),
        parcel.readParcelable(LatLngROI::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(location, flags)
        parcel.writeString(type)
        parcel.writeParcelable(viewport, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Geometry> {
        override fun createFromParcel(parcel: Parcel): Geometry {
            return Geometry(parcel)
        }

        override fun newArray(size: Int): Array<Geometry?> {
            return arrayOfNulls(size)
        }
    }
}
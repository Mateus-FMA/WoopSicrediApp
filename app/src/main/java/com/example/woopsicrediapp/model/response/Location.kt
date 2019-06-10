package com.example.woopsicrediapp.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("address_components")
    val addressComponents: AddressComponent?,
    @SerializedName("formatted_address")
    val address: String?,
    @SerializedName("geometry")
    val geometry: Geometry?,
    @SerializedName("place_id")
    val placeId: String?,
    @SerializedName("types")
    val types: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(AddressComponent::class.java.classLoader),
        parcel.readString(),
        parcel.readParcelable(Geometry::class.java.classLoader),
        parcel.readString(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(addressComponents, flags)
        parcel.writeString(address)
        parcel.writeParcelable(geometry, flags)
        parcel.writeString(placeId)
        parcel.writeStringList(types)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}
package com.example.woopsicrediapp.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class AddressComponent(
    @SerializedName("long_name")
    val longName: String?,
    @SerializedName("short_name")
    val shortName: String?,
    @SerializedName("types")
    val types: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(longName)
        parcel.writeString(shortName)
        parcel.writeStringList(types)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddressComponent> {
        override fun createFromParcel(parcel: Parcel): AddressComponent {
            return AddressComponent(parcel)
        }

        override fun newArray(size: Int): Array<AddressComponent?> {
            return arrayOfNulls(size)
        }
    }
}
package com.example.woopsicrediapp.model.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("image")
    val imageUrl: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("date")
    val date: Long?,
    @SerializedName("people")
    val people: List<Person>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.createTypedArrayList(Person)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeValue(price)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeValue(date)
        parcel.writeTypedList(people)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }
}
package com.apollo.pharmacy.ocr.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScannedMedicine(@SerializedName("artName")
                           @Expose
                           var artName: String? = null,
                           @SerializedName("artCode")
                           @Expose
                           var artCode: String? = null,
                           @SerializedName("price")
                           @Expose
                           var artprice: String? = null,
                           @SerializedName("dosage")
                           @Expose
                           var dosage: String? = null,
                           @SerializedName("qty")
                           @Expose
                           var qty: Int = 0,
                           @SerializedName("mou")
                           @Expose
                           var mou: String? = null,
                           var selectionFlag: Boolean = false) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(artName)
        parcel.writeString(artCode)
        parcel.writeString(dosage)
        parcel.writeString(artprice)
        parcel.writeInt(qty)
        parcel.writeString(mou)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ScannedMedicine> {
        override fun createFromParcel(parcel: Parcel): ScannedMedicine {
            return ScannedMedicine(parcel)
        }

        override fun newArray(size: Int): Array<ScannedMedicine?> {
            return arrayOfNulls(size)
        }
    }

}
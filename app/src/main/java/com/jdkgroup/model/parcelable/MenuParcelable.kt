package com.bytotech.model.parcelable

import android.os.Parcel
import android.os.Parcelable

/*data class MenuParcelable(val cid: String, val categoryName: String, val categoryImage: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cid)
        parcel.writeString(categoryName)
        parcel.writeString(categoryImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuParcelable> {
        override fun createFromParcel(parcel: Parcel): MenuParcelable {
            return MenuParcelable(parcel)
        }

        override fun newArray(size: Int): Array<MenuParcelable?> {
            return arrayOfNulls(size)
        }
    }
}*/

//TODO PARCELABLE GET DATA
/*
 private var parcelableTopic: List<MenuParcelable>? = null

 parcelableTopic = AppUtils.getParcelable(activity, AppConstant.BUNDLE_PARCELABLE)
        if (parcelableTopic != null) {
           logInfo(parcelableTopic!![0].categoryName)
        }
*/

data class MenuParcelable(val cid: String, val categoryName: String, val categoryImage: String): Parcelable {

    constructor(source: Parcel): this(source.readString(), source.readString(), source.readString())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.cid)
        dest?.writeString(this.categoryName)
        dest?.writeString(this.categoryImage)
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<MenuParcelable> = object : Parcelable.Creator<MenuParcelable> {
            override fun createFromParcel(source: Parcel): MenuParcelable{
                return MenuParcelable(source)
            }

            override fun newArray(size: Int): Array<MenuParcelable?> {
                return arrayOfNulls(size)
            }
        }
    }
}
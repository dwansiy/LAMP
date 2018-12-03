package com.xema.lamp.data

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Tax(
    val totalSalary: Long,
    val retirementPension: Long,
    val savingPension: Long,
    val isHandicapped: Boolean,
    val insurance: Long,
    val finalizedTax: Long,
    val createdDate :Date = Date()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readByte() != 0.toByte(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readSerializable() as Date
    )

    fun isTotalOver5500(): Boolean {
        return totalSalary > 55000000
    }

    fun getFinalInsurance(): Long {
        return if (isHandicapped) (insurance * 15 / 100)
        else (insurance * 12 / 100)
    }

    fun getFinalSavingPension(): Long {
        return if (isTotalOver5500()) (savingPension * 12 / 100)
        else (savingPension * 15 / 100)
    }

    fun getFinalRetirePension(): Long {
        return if (isTotalOver5500()) (retirementPension * 12 / 100)
        else (retirementPension * 15 / 100)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(totalSalary)
        parcel.writeLong(retirementPension)
        parcel.writeLong(savingPension)
        parcel.writeByte(if (isHandicapped) 1 else 0)
        parcel.writeLong(insurance)
        parcel.writeLong(finalizedTax)
        parcel.writeSerializable(createdDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tax> {
        override fun createFromParcel(parcel: Parcel): Tax {
            return Tax(parcel)
        }

        override fun newArray(size: Int): Array<Tax?> {
            return arrayOfNulls(size)
        }
    }
}
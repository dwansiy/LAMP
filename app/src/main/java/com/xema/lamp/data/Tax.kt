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
    val createdDate: Date = Date()
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
        val max = 1000000
        val result = if (isHandicapped) (max - insurance) * 12 / 100
        else (max - insurance) * 15 / 100
        return if (result < 0) 0 else result
    }

    fun getFinalSavingPension(): Long {
        val max = 4000000
        val result = if (isTotalOver5500()) (max - savingPension) * 12 / 100
        else (max - savingPension) * 15 / 100
        return if (result < 0) 0 else result
    }

    fun getFinalRetirePension(): Long {
        val max = 3000000
        val result = if (isTotalOver5500()) (max - retirementPension) * 12 / 100
        else (max - retirementPension) * 15 / 100
        return if (result < 0) 0 else result
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
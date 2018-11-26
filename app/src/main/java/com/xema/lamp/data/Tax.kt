package com.xema.lamp.data

data class Tax(
    val totalSalary: Long,
    val retirementPension: Long,
    val savingPension: Long,
    val isHandicapped: Boolean,
    val insurance: Long,
    val finalizedTax: Long
) {
    fun isTotalOver5500(): Boolean {
        return totalSalary >= 55000000
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
}
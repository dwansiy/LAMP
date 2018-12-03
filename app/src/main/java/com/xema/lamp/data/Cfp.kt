package com.xema.lamp.data

data class Cfp(
    val name: String,
    val license: List<String>,
    val career: List<String>,
    val rating: Float,
    val email: String,
    val profileImageResourceId: Int
) {
    fun getRatingString(): String {
        return String.format("(%.1f", rating) + " / 5)"
    }

    fun getLicenceString(): String {
        return license.joinToString(", ")
    }

    fun getCareerString(): String {
        return career.joinToString(", ")
    }
}

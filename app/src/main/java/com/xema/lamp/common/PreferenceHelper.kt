package com.xema.lamp.common

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.google.gson.Gson
import com.xema.lamp.data.Tax

import android.content.Context.MODE_PRIVATE

object PreferenceHelper {
    private const val PREF_KEY_LOCAL = "setting_data"
    private const val PREF_KEY_LOCAL_TAX = "setting_data_tax"

    fun saveTax(context: Context, tax: Tax) {
        val preferences = context.getSharedPreferences(PREF_KEY_LOCAL, MODE_PRIVATE)
        val prefsEditor = preferences.edit()
        val taxStr = Gson().toJson(tax)
        prefsEditor.putString(PREF_KEY_LOCAL_TAX, taxStr)
        prefsEditor.apply()
    }

    fun loadTax(context: Context): Tax? {
        val preferences = context.getSharedPreferences(PREF_KEY_LOCAL, MODE_PRIVATE)
        val s = preferences.getString(PREF_KEY_LOCAL_TAX, null)
        return if (!TextUtils.isEmpty(s)) Gson().fromJson(s, Tax::class.java) else null
    }

}

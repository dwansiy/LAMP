package com.xema.lamp.common

import android.content.Context
import android.net.Uri
import android.os.Build
import com.xema.lamp.BuildConfig
import com.xema.lamp.R

fun makeInquireReport(context: Context): String {
    val report: String = context.getString(R.string.report_body, getAndroidVersion(), getAppVersion())
    return "mailto:xema027@gmail.com?&body=" + Uri.encode(report)
}

fun getAndroidVersion(): String {
    val release = Build.VERSION.RELEASE
    val sdkVersion = Build.VERSION.SDK_INT
    return sdkVersion.toString() + " (" + release + ")"
}

fun getAppVersion(): String {
    return BuildConfig.VERSION_NAME
}

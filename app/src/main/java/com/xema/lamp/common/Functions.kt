package com.xema.lamp.common

import android.content.Context
import android.net.Uri
import android.os.Build
import com.xema.lamp.BuildConfig
import com.xema.lamp.R
import com.xema.lamp.data.Tax
import java.text.DateFormat
import java.text.NumberFormat

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

fun makeTaxReportString(context: Context, tax: Tax): String {
    val currencyFormat = NumberFormat.getCurrencyInstance();
    val result: String =
        context.getString(
            R.string.format_meeting,
            currencyFormat.format(tax.totalSalary),
            currencyFormat.format(tax.finalizedTax),
            currencyFormat.format(tax.retirementPension),
            currencyFormat.format(tax.savingPension),
            if (tax.isHandicapped) "예" else "아니오",
            currencyFormat.format(tax.insurance),
            DateFormat.getDateInstance(DateFormat.FULL).format(tax.createdDate)
        )
    return result
}

fun makeMeetingEmailString(context: Context, email: String, tax: Tax): String {
    return "mailto:" + email + "?&body=" + Uri.encode(
        makeTaxReportString(
            context,
            tax
        ) + "\n\n공제 상품을 안내받기 위해 재무상담을 받고 싶습니다."
    )
}

package com.xema.lamp.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.Window
import com.xema.lamp.R
import com.xema.lamp.data.Tax
import kotlinx.android.synthetic.main.dialog_tax_result.*
import java.text.DateFormat
import java.text.NumberFormat

class TaxResultDialog(val mContext: Context, private val tax: Tax) : Dialog(mContext) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE) //타이틀 바 삭제

        setContentView(R.layout.dialog_tax_result)

        object : CountDownTimer(2200, 2500) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                pb_loading.visibility = View.GONE
                tv_loading.visibility = View.GONE
                ll_container.visibility = View.VISIBLE

                val currencyFormat = NumberFormat.getCurrencyInstance();

                tv_date.text = DateFormat.getDateInstance(DateFormat.LONG).format(tax.createdDate)
                tv_total_salary.text =
                        context.getString(R.string.result_total_salary, if (tax.isTotalOver5500()) "초과" else "이하")
                             tv_finalized_tax.text =
                        context.getString(R.string.result_finalized_tax, (tax.finalizedTax / 10000).toString())
                tv_insurance.text =
                        currencyFormat.format(tax.getFinalInsurance())
                //context.getString(R.string.result_insurance, (tax.getFinalInsurance() / 10000).toString())
                tv_saving_pension.text =
                        currencyFormat.format(tax.getFinalSavingPension())
                //context.getString(R.string.result_saving_pension, (tax.getFinalSavingPension() / 10000).toString())
                tv_retirement_pension.text =
                        currencyFormat.format(tax.getFinalRetirePension())
                //context.getString(R.string.result_retirement_pension, (tax.getFinalRetirePension() / 10000).toString())

                btn_yes.setOnClickListener {
                    context.startActivity(Intent(context, CfpActivity::class.java))
                    dismiss()
                    (mContext as Activity).finish()
                }
            }
        }.start()
    }
}

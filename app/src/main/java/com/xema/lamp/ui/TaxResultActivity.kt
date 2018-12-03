package com.xema.lamp.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.xema.lamp.R
import com.xema.lamp.data.Tax
import kotlinx.android.synthetic.main.activity_tax_result.*
import java.text.NumberFormat

//Deprecated
class TaxResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tax_result)

        setUpToolbar()

        /*
        val tax: Tax = intent.getParcelableExtra<Tax>("data")

        tv_total_salary.text =
                getString(R.string.result_total_salary, if (tax.isTotalOver5500()) "초과" else "이하")
        tv_finalized_tax.text =
                getString(R.string.result_finalized_tax, (tax.finalizedTax / 10000).toString())
        tv_insurance.text =
                getString(R.string.result_insurance, (tax.getFinalInsurance() / 10000).toString())
        tv_saving_pension.text =
                getString(R.string.result_saving_pension, (tax.getFinalSavingPension() / 10000).toString())
        tv_retirement_pension.text =
                getString(R.string.result_retirement_pension, (tax.getFinalRetirePension() / 10000).toString())

        btn_yes.setOnClickListener {
            startActivity(Intent(this@TaxResultActivity, CfpActivity::class.java))
            finish()
        }
        */
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

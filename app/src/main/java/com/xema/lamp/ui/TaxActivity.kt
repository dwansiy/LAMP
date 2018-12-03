package com.xema.lamp.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast

import com.xema.lamp.R
import com.xema.lamp.common.PreferenceHelper
import com.xema.lamp.data.Tax

import kotlinx.android.synthetic.main.activity_tax.*

class TaxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tax)

        setUpToolbar()
        btn_done.setOnClickListener { attemptDone() }

        /*
        mPasswordView!!.setOnEditorActionListener(TextView.OnEditorActionListener { textView, id, keyEvent ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })
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

    private fun attemptDone() {
        edt_total_salary.error = null
        edt_retirement_pension.error = null
        edt_saving_pension.error = null
        edt_insurance.error = null
        edt_finalized_tax.error = null

        try {
            if (TextUtils.isEmpty(edt_total_salary.text)) {
                edt_total_salary.error = "총 급여를 입력해주세요"
                edt_total_salary.requestFocus()
                return
            }

            if (TextUtils.isEmpty(edt_retirement_pension.text)) {
                edt_retirement_pension.error = "퇴직연금을 입력해주세요"
                edt_retirement_pension.requestFocus()
                return
            }

            if (TextUtils.isEmpty(edt_saving_pension.text)) {
                edt_saving_pension.error = "연금저축을 입력해주세요"
                edt_saving_pension.requestFocus()
                return
            }

            if (TextUtils.isEmpty(edt_insurance.text)) {
                edt_insurance.error = "보장성 보험 금액를 입력해주세요"
                edt_insurance.requestFocus()
                return
            }

            if (TextUtils.isEmpty(edt_finalized_tax.text)) {
                edt_finalized_tax.error = "결정세액을 입력해주세요"
                edt_finalized_tax.requestFocus()
                return
            }

            val totalSalary = edt_total_salary.text.toString().toLong()
            val retirementPension = edt_retirement_pension.text.toString().toLong()
            val savingPension = edt_saving_pension.text.toString().toLong()
            val isHandicapped: Boolean = cb_handicapped.isChecked
            val insurance = edt_insurance.text.toString().toLong()
            val finalizedTax = edt_finalized_tax.text.toString().toLong()

            val tax = Tax(totalSalary, retirementPension, savingPension, isHandicapped, insurance, finalizedTax)
            //Toast.makeText(this@TaxActivity, tax.toString(), Toast.LENGTH_LONG).show()

            //val intent = Intent(this@TaxActivity, TaxResultActivity::class.java)
            //intent.putExtra("data", tax)
            //startActivity(intent)
            //finish()

            PreferenceHelper.saveTax(this@TaxActivity, tax)

            val dialog = TaxResultDialog(this@TaxActivity, tax)
            //dialog.setCanceledOnTouchOutside(false)
            dialog.show()

        } catch (e: NumberFormatException) {
            //TODO : 예외처리
            Toast.makeText(this@TaxActivity, "잘못된 입력형식입니다. 다시 한번 확인해주세요", Toast.LENGTH_LONG).show()
        }

        /*
        if (mAuthTask != null) {
            return
        }

        // Reset errors.
        mEmailView!!.error = null
        mPasswordView!!.error = null

        // Store values at the time of the login attempt.
        val email = mEmailView!!.text.toString()
        val password = mPasswordView!!.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView!!.error = getString(R.string.error_invalid_password)
            focusView = mPasswordView
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView!!.error = getString(R.string.error_field_required)
            focusView = mEmailView
            cancel = true
        } else if (!isEmailValid(email)) {
            mEmailView!!.error = getString(R.string.error_invalid_email)
            focusView = mEmailView
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView!!.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            mAuthTask = UserLoginTask(email, password)
            mAuthTask!!.execute(null as Void?)
        }
        */
    }

    /*
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

            mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
            mLoginFormView!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                (if (show) 0 else 1).toFloat()
            ).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

            mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
            mProgressView!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                (if (show) 1 else 0).toFloat()
            ).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
            mLoginFormView!!.visibility = if (show) View.GONE else View.VISIBLE
        }
    }
    */


}


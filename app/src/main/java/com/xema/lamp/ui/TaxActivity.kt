package com.xema.lamp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import com.xema.lamp.R
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

        try {
            val totalSalary = edt_total_salary.text.toString().toLong()
            val retirementPension = edt_retirement_pension.text.toString().toLong()
            val savingPension = edt_saving_pension.text.toString().toLong()
            val isHandicapped: Boolean = cb_handicapped.isChecked
            val insurance = edt_insurance.text.toString().toLong()
            val finalizedTax = edt_finalized_tax.text.toString().toLong()

            val tax = Tax(totalSalary, retirementPension, savingPension, isHandicapped, insurance, finalizedTax)
            Toast.makeText(this@TaxActivity, tax.toString(), Toast.LENGTH_LONG).show()

        } catch (e: NumberFormatException) {
            //TODO : 예외처리
            Toast.makeText(this@TaxActivity, R.string.error_common, Toast.LENGTH_LONG).show()
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


package com.xema.lamp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.xema.lamp.R
import com.xema.lamp.common.makeInquireReport
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        btn_consultant.setOnClickListener({attemptConsultant()})
        btn_tax_register.setOnClickListener({attemptTaxRegister()})
        btn_tax_help.setOnClickListener({attemptTaxHelp()})
    }

    private fun attemptConsultant(){
        //TODO CFP 연결
        Toast.makeText(this@MainActivity, getString(R.string.comming_soon), Toast.LENGTH_LONG).show()
    }

    private fun attemptTaxRegister() {
        startActivity(Intent(this@MainActivity, TaxActivity::class.java))
    }

    private fun attemptTaxHelp() {
        //todo 도움말 표시
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_nav_cfp -> {
                Toast.makeText(this@MainActivity, getString(R.string.comming_soon), Toast.LENGTH_LONG).show()
            }
            R.id.menu_nav_tax -> {
                startActivity(Intent(this@MainActivity, TaxActivity::class.java))
            }
            R.id.menu_nav_item -> {
                Toast.makeText(this@MainActivity, getString(R.string.comming_soon), Toast.LENGTH_LONG).show()
            }
            R.id.menu_nav_review -> {
                val appPackageName = packageName
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
                } catch (anfe: android.content.ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                        )
                    )
                }
            }
            R.id.menu_nav_report -> {
                val uri = Uri.parse(makeInquireReport(this))
                val it = Intent(Intent.ACTION_SENDTO, uri)
                startActivity(it)
            }
            /*
            R.id.menu_nav_version -> {
            }
            */
            //TODO : 오픈소스 라이선스, 개인정보 취급방침 등 작성
            R.id.menu_nav_term_service -> {
                Toast.makeText(this@MainActivity, getString(R.string.comming_soon), Toast.LENGTH_LONG).show()
            }
            R.id.menu_nav_term_private -> {
                Toast.makeText(this@MainActivity, getString(R.string.comming_soon), Toast.LENGTH_LONG).show()

            }
            R.id.menu_nav_open_source -> {
                Toast.makeText(this@MainActivity, getString(R.string.comming_soon), Toast.LENGTH_LONG).show()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

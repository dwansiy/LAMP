package com.xema.lamp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.Toast
import com.xema.lamp.R
import com.xema.lamp.adapter.NewsAdapter
import com.xema.lamp.common.PreferenceHelper
import com.xema.lamp.common.makeInquireReport
import com.xema.lamp.common.makeTaxReportString
import com.xema.lamp.data.News
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import java.text.NumberFormat
import java.util.*

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

        btn_consultant.setOnClickListener { attemptConsultant() }
        btn_tax_register.setOnClickListener { attemptTaxRegister() }
        btn_tax_help.setOnClickListener { attemptTaxHelp() }

        nav_view.getHeaderView(0).iv_setting.setOnClickListener {
            Toast.makeText(this@MainActivity, getString(R.string.comming_soon), Toast.LENGTH_LONG).show()
        }

        setUpAdapter()
    }

    private fun setUpAdapter() {
        val adapter = NewsAdapter(this@MainActivity, makeNewsDummies())
        rv_news.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rv_news.adapter = adapter
        rv_news.isNestedScrollingEnabled = false
        adapter.notifyDataSetChanged()
    }

    private fun makeNewsDummies(): List<News> {
        val list = ArrayList<News>()
        list.add(
            News(
                "美 10년물 금리 3.23% 돌파…4%까지 오르나",
                "7년만에 또 최고 경신.  미국 강력한 고용지표. 12월 기준금리 인상 유력.",
                "2018-10-08(월)"
            )
        )
        list.add(
            News(
                "中인민은행, 지준율 1%P 전격 인하",
                "올해 들어 네번째 유동성 확대. 외환보유액 한달새 26조 줄어.",
                "2018-10-08(월)"
            )
        )
        list.add(
            News(
                "NH·신한·우리…신탁사 인가戰 승자는 누구",
                "10년만에 부동산신탁 인가. 업황좋아 최대 3곳 인가 가능. 이르면 내주 선정 가능",
                "2018-10-08(월)"
            )
        )
        list.add(
            News(
                "중금리대출 구간 세분화…소비자 혜택 늘린다",
                "시중은행, 저축은행, 카드사 등 업권별로 중금리 차등적용. 인터넷은행은 내년 참여.",
                "2018-10-08(월)"
            )
        )
        list.add(
            News(
                "지준율인하 약발 안받네…커지는 中경제 불안감",
                "스파이칩 사태 겹쳐 중국 경제 불안감 확산.",
                "2018-10-08(월)"
            )
        )
        list.add(
            News(
                "정정 불안에 통화정책 失機까지…신흥국위기 급속 확산",
                "美금리인상 속도 빨라짐에 따라 신흥국위기 확산.",
                "2018-10-08(월)"
            )
        )
        list.add(
            News(
                "年5% 문턱…치솟는 주담대금리",
                "3년, 5년간 금리가 고정되다가 이후 변동금리로 바뀌는 혼합형 주담대. 관리 필요.",
                "2018-10-08(월)"
            )
        )


        return list
    }

    override fun onResume() {
        super.onResume()
        val tax = PreferenceHelper.loadTax(this@MainActivity)
        if (tax != null) {
            val currencyFormat = NumberFormat.getCurrencyInstance()

            tv_tax_report.text = makeTaxReportString(this@MainActivity, tax) +
                    "\n\n다음과 같이 공제가 가능합니다.\n보장성 보험 : " +
                    currencyFormat.format(tax.getFinalInsurance()) + "\n연금저축 : " +
                    currencyFormat.format(tax.getFinalSavingPension()) + "\n퇴직연금 : " +
                    currencyFormat.format(tax.getFinalRetirePension())
            btn_tax_register.text = "다시 등록하기"
        } else {
            tv_tax_report.text = getString(R.string.empty_year_tax)
            btn_tax_register.text = "등록하기"
        }
    }

    private fun attemptConsultant() {
        startActivity(Intent(this@MainActivity, CfpActivity::class.java))
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
                startActivity(Intent(this@MainActivity, CfpActivity::class.java))
            }
            R.id.menu_nav_tax -> {
                startActivity(Intent(this@MainActivity, TaxActivity::class.java))
            }
            /*
            R.id.menu_nav_item -> {
                Toast.makeText(this@MainActivity, getString(R.string.comming_soon), Toast.LENGTH_LONG).show()
            }
            */
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

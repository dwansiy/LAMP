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
                "\"반갑다, 5.5% 고금리 적금\"… 새벽부터 줄섰다",
                "수협은행 '쑥쑥크는 아이적금'… 두 달도 안 돼 10만좌 돌파 '대박'\n" +
                        "만 6세 미만 어린이가 가입 대상인 이 상품의 금리는 연 5.5%(만기 5년)다. \n" +
                        "은행권 적금 상품 금리가 연 2%대임을 감안하면 파격적인 금리 조건이다.",
                "금융상품",
                "http://biz.chosun.com/site/data/html_dir/2018/11/14/2018111403604.html"
            )
        )
        list.add(
            News(
                "집 판지 2년 넘은 신혼부부 `특별공급` 자격 인정해준다",
                "11일부터 무주택 청약 우선권\n" +
                        "기존 1주택자는 당첨되더라도 기존주택 처분해야 입주\n" +
                        "전매제한 기간 대폭 늘어나 공공택지 최대 8년, 민간 4년",
                "2018. 12. 8",
                "http://estate.mk.co.kr/news2011/view.php?year=2018&no=766533"
            )
        )
        list.add(
            News(
                "빚 못갚는 서민…대부업 연체율 급등",
                "대부업체 연체율 10.7%. 집계이후 최고치.\n" +
                        "저축은행 연체율도 3배로. 저신용자 대출 더 좁아져\n" +
                        "\"불법사금융 가기 전 개인회생 활용을\"",
                "2018. 12. 7",
                "https://m.mk.co.kr/news/home/2018/761131#mkmain"
            )
        )
        list.add(
            News(
                "`여의도 116배` 군사보호구역 푼다…민통선 땅값 들썩",
                "경기북부, 강원 접경지역들 전국서 3억 3699만 제곱미터 해제\n" +
                        "\"그린벨트 해제와 같은 효과\" 건축물 신축, 증축 쉬워져\n" +
                        "\"개발까지 시간걸려\" 신중론도.",
                "2018. 12. 7",
                "https://news.mk.co.kr/newsRead.php?sc=&cm=&no=761124&year=2018"
            )
        )
        list.add(
            News(
                "팔 걷어붙인 금감원…취약층 `빚 탕감` 나서",
                "내년 상반기중 추진. 도덕적 해이 논란 커질수도.\n" +
                        "실업·폐업·질병 등 위기때 원금 감면…최대 45% 논의",
                "2018. 12. 7",
                "https://m.mk.co.kr/news/headline/2018/761110#mkmain"
            )
        )
        list.add(
            News(
                "국채 장기금리 1%대 위협…경기침체 시그널 뚜렷",
                "한은 기준금리 인상에 오히려 경기위축 우려 커져.\n" +
                        "장기금리는 더 떨어지고 장단기물 격차도 좁혀져.\n" +
                        "3년-10년 만기 국채 금리차 한달새 0.2 -> 157%P",
                "2018. 12. 7",
                "https://news.mk.co.kr/newsRead.php?year=2018&no=761108"
            )
        )
        list.add(
            News(
                "美·러·사우디 원유놓고 `이합집산`…불확실성 커진 유가",
                "美, 사우디에 증산 압박. 러·사우디는 감산 합의.\n" +
                        "카타르 OPEC 탈퇴에 원유카르텔 붕괴 전망도.\n" +
                        "유가, 감산연장 기대 4%↑ 향후 유가 전망은 불투명",
                "2018. 12. 6",
                "https://m.mk.co.kr/news/headline/2018/758390#mkmain"
            )
        )
        list.add(
            News(
                "\"개인 신용등급, 우리가 올려드려요\"",
                "카뱅, 토스 등 '신용관리' 경쟁\n" +
                        "신용점수를 높일 수 있도록 도와주는 개인 맞춤형 간편 서비스가 잇따라 출시",
                "2018. 12. 5",
                "http://mbnmoney.mbn.co.kr/news/view?news_no=MM1003419029"
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

package com.xema.lamp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.xema.lamp.R
import com.xema.lamp.adapter.CfpAdapter
import com.xema.lamp.data.Cfp
import kotlinx.android.synthetic.main.activity_cfp.*
import java.util.*

class CfpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cfp)

        setUpToolbar()
        setUpAdapter()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }
    }

    private fun setUpAdapter() {
        val adapter = CfpAdapter(this@CfpActivity,makeDummies())
        rv_cfp.layoutManager = LinearLayoutManager(this@CfpActivity, RecyclerView.VERTICAL, false)
        rv_cfp.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun makeDummies(): List<Cfp> {
        val list = ArrayList<Cfp>()
        val one = Cfp(
            "안도원",
            Arrays.asList("CFP"),
            Arrays.asList("메트라이트 생명 2년", "VALUE MARK 재직 중"),
            4.5f,
            "do_peach520@naver.com",
            R.drawable.ic_profile_default
        )
        val two = Cfp(
            "윤승일",
            Arrays.asList("CFP"),
            Arrays.asList("메트라이트 생명 4년", "VALUE MARK 재직 중"),
            4.8f,
            "rhvx@naver.com",
            R.drawable.ic_profile_default
        )
        list.add(one)
        list.add(two)
        return list
    }

}

package com.xema.lamp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.xema.lamp.R
import com.xema.lamp.common.PreferenceHelper
import com.xema.lamp.common.makeMeetingEmailString
import com.xema.lamp.data.Cfp
import com.xema.lamp.ui.TaxActivity
import kotlinx.android.synthetic.main.item_cfp.view.*

class CfpAdapter(private val context: Context, private val list: List<Cfp>) :
    RecyclerView.Adapter<CfpAdapter.ListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cfp, parent, false)
        return ListItemViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val cfp = list[position]

        Glide.with(context).load(cfp.profileImageResourceId).into(holder.rivProfile)
        holder.tvName.text = cfp.name
        holder.rvRating.rating = cfp.rating
        holder.tvRating.text = cfp.getRatingString()
        holder.tvLicence.text = cfp.getLicenceString()
        holder.tvEmail.text = cfp.email
        holder.tvCareer.text = cfp.getCareerString()
        holder.btnContact.setOnClickListener {
            val tax = PreferenceHelper.loadTax(context)
            if (tax == null) {
                Snackbar.make(holder.itemView, "연말정산 내역을 먼저 등록해주세요", Snackbar.LENGTH_LONG)
                    .setActionTextColor(ContextCompat.getColor(context, R.color.colorYellow)).setAction("등록하기") {
                    context.startActivity(Intent((context), TaxActivity::class.java))
                }.show()
            } else {
                val uri = Uri.parse(makeMeetingEmailString(context, cfp.email, tax))
                context.startActivity(Intent(Intent.ACTION_SENDTO, uri))
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ListItemViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        val rivProfile = itemView.riv_profile
        val tvName = itemView.tv_name
        val rvRating = itemView.rb_rating
        val tvRating = itemView.tv_rating
        val tvLicence = itemView.tv_licence
        val tvEmail = itemView.tv_email
        val tvCareer = itemView.tv_career
        val btnContact = itemView.btn_contact
    }
}

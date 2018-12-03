package com.xema.lamp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xema.lamp.R
import com.xema.lamp.data.News
import kotlinx.android.synthetic.main.item_news.view.*
import java.text.DateFormat

class NewsAdapter(private val context: Context, private val list: List<News>) :
    RecyclerView.Adapter<NewsAdapter.ListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ListItemViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val news = list[position]

        holder.tvTitle.text = news.title
        holder.tvDescription.text = news.description
        //holder.tvDate.text = DateFormat.getDateInstance(DateFormat.FULL).format(news.date)
        holder.tvDate.text = news.date
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ListItemViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_title
        val tvDescription = itemView.tv_description
        val tvDate = itemView.tv_date

    }
}

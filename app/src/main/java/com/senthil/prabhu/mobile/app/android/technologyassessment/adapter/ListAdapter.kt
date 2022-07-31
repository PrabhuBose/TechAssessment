package com.senthil.prabhu.mobile.app.android.technologyassessment.adapter

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.senthil.prabhu.mobile.app.android.technologyassessment.R
import com.senthil.prabhu.mobile.app.android.technologyassessment.views.NewsDetail
import com.senthil.prabhu.mobile.app.android.technologyassessment.databinding.ItemForNewsListBinding
import com.senthil.prabhu.mobile.app.android.technologyassessment.models.Results
import com.senthil.prabhu.mobile.app.android.technologyassessment.utils.CircleTransform
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation


class ListAdapter(private val context: Context, private val aList: List<Results>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemForNewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemForNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var articles : Results
        with(holder) {
            with(aList[position]) {
                articles = this
                binding.newsTitle.text = this.title
                binding.byTv.text = this.byline
                binding.authorTv.text = this.section
                binding.dateTv.text = this.published_date

                if (media.isNotEmpty())
                    Picasso.with(context).load(this.media[0].mediaMetadata[2].url)
                        .transform(CircleTransform())
                        .into(binding.thumbNail)
                else
                    binding.thumbNail.setImageResource(R.drawable.no_image)
            }

            binding.root.setOnClickListener {
                val intent = Intent(context, NewsDetail::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
                intent.putExtra("article",articles)
                val bundle = Bundle()
                startActivity(context, intent, bundle);
            }
        }
    }

    override fun getItemCount(): Int {
        return aList.size
    }

}
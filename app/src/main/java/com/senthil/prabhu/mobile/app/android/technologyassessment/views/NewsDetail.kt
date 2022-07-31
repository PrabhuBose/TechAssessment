package com.senthil.prabhu.mobile.app.android.technologyassessment.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.senthil.prabhu.mobile.app.android.technologyassessment.R
import com.senthil.prabhu.mobile.app.android.technologyassessment.models.Results
import com.senthil.prabhu.mobile.app.android.technologyassessment.databinding.ActivityNewsDetailBinding
import com.senthil.prabhu.mobile.app.android.technologyassessment.databinding.NewsDetailsToolbarBinding
import com.squareup.picasso.Picasso
import java.util.*

class NewsDetail : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding
    private lateinit var mergeBinding: NewsDetailsToolbarBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        mergeBinding = NewsDetailsToolbarBinding.bind(binding.root)
        setContentView(binding.root)
        setSupportActionBar(mergeBinding.toolbar1)
        supportActionBar!!.setDisplayShowTitleEnabled(false);
        Objects.requireNonNull(supportActionBar)!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.elevation = 10.0f
        mergeBinding.title.text = getString(R.string.news_details_heading)

        val article = intent.extras!!.get("article") as Results

        binding.articleHeading.text = article.title
        binding.articleDetails.text = article.abstract


        if (article.media.isNotEmpty()) {
            binding.captionAndCopyright.text = article.media[0].caption + " " +article.media[0].copyright
            Picasso.with(this).load(article.media[0].mediaMetadata[2].url)
                .resize(4000, 1000)
                .centerInside()
                .into(binding.articleImage)
        }else
            binding.articleImage.setImageResource(R.drawable.no_image)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
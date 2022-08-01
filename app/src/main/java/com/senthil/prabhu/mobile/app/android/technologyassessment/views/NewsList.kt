package com.senthil.prabhu.mobile.app.android.technologyassessment.views

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.senthil.prabhu.mobile.app.android.technologyassessment.R
import com.senthil.prabhu.mobile.app.android.technologyassessment.adapter.ListAdapter
import com.senthil.prabhu.mobile.app.android.technologyassessment.api.Api
import com.senthil.prabhu.mobile.app.android.technologyassessment.constants.Constants
import com.senthil.prabhu.mobile.app.android.technologyassessment.databinding.ActivityNewsListBinding
import com.senthil.prabhu.mobile.app.android.technologyassessment.databinding.ActivityNewsListBinding.inflate
import com.senthil.prabhu.mobile.app.android.technologyassessment.databinding.NewsListToolbarBinding
import com.senthil.prabhu.mobile.app.android.technologyassessment.models.Articles
import com.senthil.prabhu.mobile.app.android.technologyassessment.models.Results
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsList : AppCompatActivity() {
    private lateinit var binding: ActivityNewsListBinding
    private lateinit var mergeBinding: NewsListToolbarBinding
    private lateinit var articlesApi: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        mergeBinding = NewsListToolbarBinding.bind(binding.root)
        setContentView(binding.root)
        setSupportActionBar(mergeBinding.toolbar1)
        mergeBinding.title.text = getString(R.string.news_list_heading)
        articlesApi = Api.create()
        getArticlesByDays(Constants.ONE_WEEK)

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getArticlesByDays(days: String) {
        GlobalScope.launch {
            try {
                binding.errorMessage.visibility = View.GONE
                binding.recyclerView.visibility = View.INVISIBLE
                setArticleList(getArticles(days).results)
            } catch (e: Exception) {
                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.today -> {
                binding.progressBar.visibility = View.VISIBLE
                getArticlesByDays(Constants.TODAY)
                true
            }
            R.id.one_week -> {
                binding.progressBar.visibility = View.VISIBLE
                getArticlesByDays(Constants.ONE_WEEK)
                true
            }
            R.id.one_month -> {
                binding.progressBar.visibility = View.VISIBLE
                getArticlesByDays(Constants.ONE_MONTH)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private suspend fun getArticles(days: String): Articles {
        return articlesApi.getArticles(days, Constants.NY_API_KEY)
    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun setArticleList(results: List<Results>) {
        GlobalScope.launch(Dispatchers.Main) {
            val linearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            val listAdapter = ListAdapter(applicationContext, results)
            with(binding.recyclerView) {
                layoutManager = linearLayoutManager
                adapter = listAdapter
            }
            binding.recyclerView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.errorMessage.visibility = View.GONE
        }
    }
}
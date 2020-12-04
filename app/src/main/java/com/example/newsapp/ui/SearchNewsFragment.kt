package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.SongAdapter
import com.example.newsapp.db.ArticleDao

import com.example.newsapp.db.NewsViewModel
import com.example.newsapp.model.Article
import com.example.newsapp.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.android.synthetic.main.fragment_breaking_news.paginationProgressBar
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.android.synthetic.main.item_article_preview.*
import kotlinx.coroutines.*


class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    val TAG = "SearchNewsFragment"

    lateinit var viewModel: NewsViewModel
    lateinit var songAdapter:SongAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        songAdapter.setOnItemClickListener {

val name = it.artistName
            val collection = it.collectionName
            val price = it.collectionPrice
            val trackName = it.trackName
            val collectionPrice = it.collectionPrice
            val article = Article(name,collection,price,trackName,collectionPrice)
            GlobalScope.launch { viewModel.newsRepository.upsert(article) }
            Toast.makeText(activity,"Saved in Room Database",Toast.LENGTH_LONG).show()
        }
        var job: Job? = null
        etSearch.addTextChangedListener{editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if(editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }
        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        songAdapter.differ.submitList(newsResponse.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }
    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        songAdapter = SongAdapter()
        rvSearchNews.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
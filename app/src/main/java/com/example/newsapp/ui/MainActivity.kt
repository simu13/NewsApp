package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.db.SongViewModelProviderFactory
import com.example.newsapp.R
import com.example.newsapp.db.SongRepository
import com.example.newsapp.db.SongViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: SongViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = SongRepository(ArticleDatabase(this))
        val viewModelProviderFactory = SongViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(SongViewModel::class.java)
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
    }
}

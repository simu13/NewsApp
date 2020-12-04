package com.example.newsapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapters.SongAdapter
import com.example.newsapp.db.SongViewModel
import kotlinx.android.synthetic.main.fragment_saved.*


class SavedFragment : Fragment(R.layout.fragment_saved) {

    lateinit var viewModel: SongViewModel
    lateinit var songAdapter: SongAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()



        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        )
        {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                 val adapter = songAdapter.differ.currentList[position]
                //viewModel.deleteArticle(adapter)
            }

        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedNews)
        }

        /*viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
        newsAdapter.differ.submitList(articles)
        })*/

        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles->
            songAdapter.differ.submitList(articles)
        })

    }

    private fun setupRecyclerView() {
        songAdapter = SongAdapter()
        rvSavedNews.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
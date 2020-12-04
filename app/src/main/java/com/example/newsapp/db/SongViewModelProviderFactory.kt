package com.example.newsapp.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SongViewModelProviderFactory(
    val songRepository: SongRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SongViewModel(songRepository) as T
    }
}
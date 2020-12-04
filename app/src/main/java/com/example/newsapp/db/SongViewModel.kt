package com.example.newsapp.db

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.SongResponse
import com.example.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SongViewModel(
    val songRepository: SongRepository
) : ViewModel() {



    val searchNews: MutableLiveData<Resource<SongResponse>> = MutableLiveData()
    var searchNewsResponse:SongResponse? = null



    fun searchNews(searchQuery : String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = songRepository.searchNews(searchQuery)
        searchNews.postValue(handleSearchNewsResponse(response))
    }


    private fun handleSearchNewsResponse(response: Response<SongResponse>) : Resource<SongResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (searchNewsResponse == null){
                    searchNewsResponse = resultResponse
                }
                else{
                    //val oldArticle = searchNewsResponse?.articles
                    //val newsArticle = resultResponse.articles
                    //oldArticle?.addAll(newsArticle)
                }
                return Resource.Success(searchNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    /*fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }


    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }*/
    fun getSavedNews() = songRepository.getSavedNews()
}
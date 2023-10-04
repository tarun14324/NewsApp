package com.example.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.Article
import com.example.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    private val _newsArticles = MutableLiveData<List<Article>>()
    val newsArticles: LiveData<List<Article>>
        get() = _newsArticles

    private val _errorData = MutableLiveData<String>()
    val errorData: LiveData<String>
        get() = _errorData

    init {
        viewModelScope.launch {
            getNews()
        }
    }


    private suspend fun getNews() {
        try {
            val response = repository.getNews()
            if (response.isSuccessful) {
                val newsData = response.body()
                if (newsData != null) {
                    _newsArticles.postValue(newsData.articles)
                } else {
                    _errorData.postValue("Response body is null")
                }
            } else {
                _errorData.postValue(
                    "Request failed with code ${response.code()}"
                )
            }
        } catch (e: IOException) {
            _errorData.postValue("Network error: ${e.message}")
        } catch (e: Exception) {
            _errorData.postValue("An error occurred: ${e.message}")
        }
    }
}

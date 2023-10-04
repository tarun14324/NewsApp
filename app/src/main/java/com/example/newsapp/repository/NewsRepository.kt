package com.example.newsapp.repository

import com.example.newsapp.model.News
import com.example.newsapp.retrofit.ApiHelper
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getNews(): Response<News> {
        return apiHelper.getNews()

    }
}

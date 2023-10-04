package com.example.newsapp.retrofit

import com.example.newsapp.model.News
import retrofit2.Response

/**
 *interface to fetch data using retrofit
 * */
interface ApiHelper {
    // get news from api
    suspend fun getNews(): Response<News>
}
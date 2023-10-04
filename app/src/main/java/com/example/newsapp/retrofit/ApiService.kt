package com.example.newsapp.retrofit

import com.example.newsapp.model.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/**
 *api service class
 * */
interface ApiService {
    // get news from api
    @GET("health/in.json")
    suspend fun getJsonDataFromUrl(): Response<News>

}
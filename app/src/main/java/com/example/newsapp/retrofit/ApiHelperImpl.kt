package com.example.newsapp.retrofit

import com.example.newsapp.model.News
import retrofit2.Response
import javax.inject.Inject


/**
 * class that extends with ApiHelper class to fetch the data
 * */
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    // get products from api
    override suspend fun getNews(): Response<News> = apiService.getJsonDataFromUrl()


}
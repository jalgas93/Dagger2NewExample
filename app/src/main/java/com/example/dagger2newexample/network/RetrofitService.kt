package com.example.dagger2newexample.network

import com.example.dagger2newexample.network.paging.PagingModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query



interface RetrofitService {

    companion object {
        const val ENDPOINT = "https://food2fork.ca/api/recipe/"
    }

    @GET("get")
    suspend fun getRecipe(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): com.example.dagger2newexample.network.RetrofitModel

    @GET("search")
    suspend fun pageRecipe(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String,
    ): PagingModel

}
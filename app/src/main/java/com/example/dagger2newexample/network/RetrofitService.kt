package com.example.dagger2newexample.network

import com.example.dagger2newexample.network.paging.PagingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitService {

    companion object{
        const val ENDPOINT = "https://food2fork.ca/api/recipe/"
    }


@GET("get")
 suspend fun getRecipe(
    @Header("Authorization")token:String,
    @Query("id")id:Int
):RetrofitModel

 @GET("search")
 suspend fun pageRecipe(
     @Header("Authorization") token:String,
     @Query("query") query:String,
     @Query("page")page:Int
 ):PagingModel

}
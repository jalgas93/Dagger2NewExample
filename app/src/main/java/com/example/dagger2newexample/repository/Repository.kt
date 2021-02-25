package com.example.dagger2newexample.repository

import com.example.dagger2newexample.network.RetrofitService
import javax.inject.Inject

class Repository @Inject constructor(private val retrofitService: RetrofitService) {

 suspend fun getRecipe(token:String,recipeId:Int) =retrofitService.getRecipe(token,recipeId)

}
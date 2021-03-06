package com.example.dagger2newexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.dagger2newexample.network.RetrofitModel
import com.example.dagger2newexample.network.RetrofitService
import com.example.dagger2newexample.utils.DataState
import retrofit2.http.Query
import javax.inject.Inject

class Repository @Inject constructor(private val retrofitService: RetrofitService) {

    fun repos(token: String, recipeId: Int): LiveData<DataState<RetrofitModel>> = liveData {

        try {
           emit(DataState.loading())
            kotlinx.coroutines.delay(1000)
           var recipe = retrofitService.getRecipe(token,recipeId)
           // var recipe = getRecipeFromCache(recipeId)

            if (recipe !=null){
                emit(DataState.success(recipe))
            } else{

               // retrofitService.getRecipe(token,recipeId)

            }


        } catch (e: Exception) {

        }

    }

    private fun getRecipeFromCache(recipeId: Int){



    }

   // suspend fun getRecipe(token: String, recipeId: Int) = retrofitService.getRecipe(token, recipeId)




}
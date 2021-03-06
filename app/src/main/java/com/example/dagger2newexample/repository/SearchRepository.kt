package com.example.dagger2newexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.dagger2newexample.network.RetrofitService
import com.example.dagger2newexample.network.paging.PagingModel
import com.example.dagger2newexample.utils.DataState
import javax.inject.Inject

class SearchRepository @Inject constructor(var retrofitService: RetrofitService) {

    fun searchRecipe(
        token: String,
        query: String,
        page: Int
    ): LiveData<DataState<PagingModel>> = liveData {
       try {
           emit(DataState.loading())
           kotlinx.coroutines.delay(500)

           var searchrecipe = retrofitService.pageRecipe(token, query, page)

           if (searchrecipe !=null){

               emit(DataState.success(searchrecipe))
           }else{

           }


       }catch (e:Exception){

       }
    }
}
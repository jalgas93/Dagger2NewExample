package com.example.dagger2newexample.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData


import com.example.dagger2newexample.cache.database.AppDatabase
import com.example.dagger2newexample.cache.model.RoomMapper
import com.example.dagger2newexample.cache.model.RoomModel
import com.example.dagger2newexample.network.RetrofitMapper
import com.example.dagger2newexample.network.RetrofitModel
import com.example.dagger2newexample.network.RetrofitService
import com.example.dagger2newexample.network.paging.RecipeRemoteMediator

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    var retrofitService: RetrofitService,
    var database: AppDatabase,
    var roomMapper: RoomMapper,
    var retrofitMapper: RetrofitMapper

) {


//    fun searchRecipe(
//        token: String,
//        query: String,
//        page: Int
//    ): LiveData<DataState<PagingModel>> = liveData {
//       try {
//           emit(DataState.loading())
//           kotlinx.coroutines.delay(300)
//
//           var searchrecipe = retrofitService.pageRecipe(token, page, query)
//
//           if (searchrecipe !=null){
//
//               emit(DataState.success(searchrecipe))
//           }else{
//           }
//
//       }catch (e:Exception){
//
//       }
//    }

//    fun a ():Flow<PagingData<RoomModel>>{
//        return Pager(
//
//        ),
//
//    }
@OptIn(ExperimentalPagingApi::class)
    fun getSearchResult(query: String): Flow<PagingData<RoomModel>> {

    val dbQuery = "%${query.replace(' ', '%')}%"

    val pagingSourceFactory = {

        database.roomDao().foodByName(dbQuery)
    }

        return Pager(
           config =  PagingConfig(pageSize = NETWORK_PAGE_SIZE,enablePlaceholders = false),
            remoteMediator = RecipeRemoteMediator(
                query,
                retrofitService,
                database,roomMapper, retrofitMapper
            ),
             pagingSourceFactory = pagingSourceFactory
       ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}
package com.example.dagger2newexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.dagger2newexample.cache.RoomDao
import com.example.dagger2newexample.cache.model.RoomMapper
import com.example.dagger2newexample.model.Model
import com.example.dagger2newexample.network.RetrofitMapper
import com.example.dagger2newexample.network.RetrofitModel
import com.example.dagger2newexample.network.RetrofitService
import com.example.dagger2newexample.utils.DataState
import retrofit2.http.Query
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofitService: RetrofitService,
    private val roomDao: RoomDao,
    private val roomMapper: RoomMapper,
    private val retrofitMapper: RetrofitMapper,


    ) {

    fun repos(token: String, recipeId: Int): LiveData<DataState<List<Model>>> = liveData {

        try {
            emit(DataState.loading())
            kotlinx.coroutines.delay(1000)

            var recipe = getRecipeFromCache(recipeId)

            if (recipe != null) {
                emit(DataState.success(recipe))
            } else {

                roomDao.insert(
                    roomMapper.mapFromDomainModel(
                        getRecipeFromNetwork(token, recipeId)
                    )
                )

            }


        } catch (e: Exception) {

        }

    }

    private suspend fun getRecipeFromNetwork(token: String, recipeId: Int): Model {
        return retrofitMapper.mapToDomainModel(
            retrofitService.getRecipe(token, recipeId)
        )

    }

    private suspend fun getRecipeFromCache(recipeId: Int): List<Model> {
        return roomDao.getRecipeById(recipeId)?.let {
            roomMapper.mapToDomainModelToList(it)
        }


    }

    //  suspend fun getRecipe(token: String, recipeId: Int) = retrofitService.getRecipe(token, recipeId)

}
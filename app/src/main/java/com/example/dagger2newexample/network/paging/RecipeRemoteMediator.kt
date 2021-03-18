package com.example.dagger2newexample.network.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.example.dagger2newexample.cache.RoomDao
import com.example.dagger2newexample.cache.database.AppDatabase
import com.example.dagger2newexample.cache.model.RoomMapper
import com.example.dagger2newexample.cache.model.RoomModel
import com.example.dagger2newexample.model.Model
import com.example.dagger2newexample.network.RetrofitMapper
import com.example.dagger2newexample.network.RetrofitModel
import com.example.dagger2newexample.network.RetrofitService
import retrofit2.HttpException
import java.io.InvalidObjectException
import java.security.PrivateKey
import javax.inject.Inject


private const val STARTING_PAGE_INDEX = 1
private const val token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"

@OptIn(ExperimentalPagingApi::class)
class RecipeRemoteMediator @Inject constructor(
    private val query: String,
    private val retrofitService: RetrofitService,
    private val roomDatabase: AppDatabase,
    private val roomMapper: RoomMapper,
    private val retrofitMapper: RetrofitMapper
) : RemoteMediator<Int, RoomModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RoomModel>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestCurrentPositioin(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX


            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys == null || remoteKeys.nextKey == null) {
                    throw InvalidObjectException(" Remote key will not be null")
                }
                remoteKeys.nextKey

            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                if (remoteKeys == null) {
// LoadType - PREPEND, поэтому некоторые данные были загружены раньше,
                    // чтобы мы могли получить удаленные ключи
                    // Если удаленные ключи равны нулю, значит, мы недопустимое состояние и у нас есть ошибка
                    throw InvalidObjectException("Remote key and the prevKey should not be null")
                }
                val prevKey = remoteKeys.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                remoteKeys.prevKey
            }


        }

        val apiQuery = query
        try {
            val apiResponce = retrofitService.pageRecipe(token, 2, "Pizza")
            val repos = apiResponce.results
            Log.i("jalgas5",repos.toString())
            val endOfPagination = repos!!.isEmpty()
            roomDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    roomDatabase.roomDao().clearRetrofitModel()
                    roomDatabase.remoteKeysDao().clearRemoteKeys()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPagination) null else page + 1
                val keys = repos.map {
                    RemoteKeys(
                        repoId = it.pk, prevKey = prevKey, nextKey = nextKey
                    )
                }
                roomDatabase.remoteKeysDao().insertAll(keys)
                roomDatabase.roomDao().insertFood(
                    roomMapper.mapFromDomainModelToList(
                        retrofitMapper.mapToDomainModelList(repos)
                    )

                )
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPagination)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }

    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, RoomModel>): RemoteKeys? {
// Получить последнюю полученную страницу, содержащую элементы.
        // С этой последней страницы получаем последний элемент
        return state.pages.lastOrNull() {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {
// Получаем удаленные ключи первых извлеченных элементов
            roomDatabase.remoteKeysDao().remoteKeysRepoId(it.id)
        }
    }
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, RoomModel>): RemoteKeys? {

// Получить первую полученную страницу, содержащую элементы.
        // С этой первой страницы получаем первый элемент

        return state.pages.firstOrNull() {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {
            roomDatabase.remoteKeysDao().remoteKeysRepoId(it.id)
        }
    }

    private suspend fun getRemoteKeyClosestCurrentPositioin(state: PagingState<Int, RoomModel>): RemoteKeys? {

// Библиотека подкачки пытается загрузить данные после позиции привязки
        // Получаем элемент, ближайший к позиции привязки

        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id?.let {
                roomDatabase.remoteKeysDao().remoteKeysRepoId(it)
            }
        }
    }

    // private suspend fun getRecipeFromNetwork(token:String,)


}
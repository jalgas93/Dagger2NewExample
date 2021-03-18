package com.example.dagger2newexample.network.paging
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//
//import com.example.dagger2newexample.network.RetrofitModel
//import com.example.dagger2newexample.network.RetrofitService
//
//import com.example.dagger2newexample.repository.SearchRepository.Companion.NETWORK_PAGE_SIZE
//
//import retrofit2.HttpException
//import java.io.IOException
//import javax.inject.Inject

//private const val GITHUB_STARTING_PAGE_INDEX = 1
//
//
//class GitHubPagingSourse @Inject constructor(
//    private val query: String,
//    private val retrofitService: RetrofitService
//) : PagingSource<Int, RetrofitModel>() {
//    override fun getRefreshKey(state: PagingState<Int, RetrofitModel>): Int? {
//
//        return state.anchorPosition?.let {
//            state.closestPageToPosition(it)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
//        }
//
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RetrofitModel> {
//        val position = params.key ?: GITHUB_STARTING_PAGE_INDEX
//        val apiQuery = query
//        val token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
//
//        return try {
//            val response = retrofitService.pageRecipe(token, position,apiQuery)
//            val repos = response.results
//
//            val nextKey = if (repos!!.isEmpty()) {
//                null
//            } else {
//                position + (params.loadSize / NETWORK_PAGE_SIZE)
//            }
//            LoadResult.Page(
//                data = repos,
//                prevKey = if (position == GITHUB_STARTING_PAGE_INDEX) null else position - 1,
//                nextKey = nextKey
//            )
//
//        } catch (exception: IOException) {
//            LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            LoadResult.Error(exception)
//        }
//
//    }
//}
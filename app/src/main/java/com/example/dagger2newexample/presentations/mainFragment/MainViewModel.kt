package com.example.dagger2newexample.presentations.mainFragment

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.dagger2newexample.cache.model.RoomModel
import com.example.dagger2newexample.model.Model
import com.example.dagger2newexample.network.RetrofitModel
import com.example.dagger2newexample.network.RetrofitService
import com.example.dagger2newexample.repository.Repository
import com.example.dagger2newexample.repository.SearchRepository
import com.example.dagger2newexample.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


class MainViewModel @Inject constructor(
    var repository: Repository,
    var searchRepository: SearchRepository,
    private @Named("auth_token") val token: String,
) : ViewModel() {

//
//    private val recipe: MutableLiveData<DataState<List<Model>>> = MutableLiveData()
//    val liveData: LiveData<DataState<List<Model>>> get() = recipe


    // fun getRecipeRepos(recipeId: Int) = repository.repos(token, recipeId)

//   suspend fun getRecipe(recipeId:Int){
//        viewModelScope.launch {
//            repository.repos(token, recipeId)
//        }
//    }


    // fun search(query:String,page:Int) = searchRepository.searchRecipe(token, query, page)


    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<RetrofitModel>>? = null

    fun searchRepo(queryString: String): Flow<PagingData<RetrofitModel>> {
        val lastResult = currentSearchResult

        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult

        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<RetrofitModel>> = searchRepository.getSearchResult(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}
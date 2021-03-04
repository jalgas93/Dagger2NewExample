package com.example.dagger2newexample.presentations.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger2newexample.model.Model
import com.example.dagger2newexample.network.RetrofitModel
import com.example.dagger2newexample.network.RetrofitService
import com.example.dagger2newexample.repository.Repository
import com.example.dagger2newexample.utils.DataState
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(var repository: Repository,
private @Named("auth_token") val token: String
                                        ):ViewModel() {



    private val recipe:MutableLiveData<DataState<List<Model>>> = MutableLiveData()
     val liveData:LiveData<DataState<List<Model>>> get()  = recipe



    fun getRecipeRepos(recipeId: Int) = repository.repos(token, recipeId)

   suspend fun getRecipe(recipeId:Int){
        viewModelScope.launch {
            repository.repos(token, recipeId)
        }
    }


}
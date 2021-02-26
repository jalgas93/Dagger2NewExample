package com.example.dagger2newexample.presentations.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger2newexample.network.RetrofitModel
import com.example.dagger2newexample.network.RetrofitService
import com.example.dagger2newexample.repository.Repository
import com.example.dagger2newexample.utils.DataState
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(var repository: Repository,
private @Named("auth_token") val token: String
                                        ):ViewModel() {



    private val recipe:MutableLiveData<RetrofitModel> = MutableLiveData()
     val liveData:LiveData<RetrofitModel> get()  = recipe



   suspend fun getRecipe(recipeId:Int){
        viewModelScope.apply {
           recipe.value =  repository.getRecipe(token, recipeId)
        }
    }


}
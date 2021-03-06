package com.example.dagger2newexample.network.paging


import com.example.dagger2newexample.network.RetrofitModel
import com.google.gson.annotations.SerializedName

data class PagingModel(
    @SerializedName("count")
    var count: Int, // 118
    @SerializedName("next")
    var next: String?, // http://127.0.0.1:8000/api/recipe/search/?page=3&query=beef+carrot+potato+onion
    @SerializedName("previous")
    var previous: String?, // https://food2fork.ca/api/recipe/search/?query=beef+carrot+potato+onion
    @SerializedName("results")
    var results: List<RetrofitModel>?
)
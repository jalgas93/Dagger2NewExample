package com.example.dagger2newexample.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RetrofitModel (
    @SerializedName("pk")
    var pk: Int, // 583
    @SerializedName("title")
    var title: String?, // Pizza Potato Skins
    @SerializedName("publisher")
    var publisher: String?, // mitch
    @SerializedName("featured_image")
    var featuredImage: String?, // https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/583/featured_image.png
    @SerializedName("rating")
    var rating: Int, // 16
    @SerializedName("source_url")
    var sourceUrl: String?, // http://thepioneerwoman.com/cooking/2013/04/pizza-potato-skins/
    @SerializedName("description")
    var description: String?, // N/A
    @SerializedName("cooking_instructions")
    var cookingInstructions: String?, // null
    @SerializedName("ingredients")
    var ingredients: List<String>?,
    @SerializedName("date_added")
    var dateAdded: String?, // November 11 2020
    @SerializedName("date_updated")
    var dateUpdated: String? // November 11 2020
):Serializable
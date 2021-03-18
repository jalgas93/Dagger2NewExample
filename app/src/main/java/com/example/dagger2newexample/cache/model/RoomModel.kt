package com.example.dagger2newexample.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tableNameFood")
data class RoomModel(






    // Value from API
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    // Value from API
    @ColumnInfo(name = "title")
    var title: String?,

    // Value from API
    @ColumnInfo(name = "publisher")
    var publisher: String? = null,

    // Value from API
    @ColumnInfo(name = "featured_image")
    var featuredImage: String? = null,

    // Value from API
    @ColumnInfo(name = "rating")
    var rating: Int = 0,

    // Value from API
    @ColumnInfo(name = "source_url")
    var sourceUrl: String? = null,

    // Value from API
    @ColumnInfo(name = "description")
    var description: String? = null,

    // Value from API
    @ColumnInfo(name = "cooking_instructions")
    var cookingInstructions: String? = null,

    /**
     * Value from API
     * Comma separated list of ingredients
     * EX: "carrots, cabbage, chicken,"
     */
    @ColumnInfo(name = "ingredients")
    var ingredients: String? = null,

    /**
     * Value from API
     * EX: "November 11 2020"
     */
    @ColumnInfo(name = "date_added")
    var dateAdded: String? = null,

    /**
     * Value from API
     * EX: "November 11 2020"
     */
    @ColumnInfo(name = "date_updated")
    var dateUpdated: String? = null,

    /**
     * The date this recipe was "refreshed" in the cache.
     * EX: TODO("Add format example")
     */
    @ColumnInfo(name = "date_cached")
    var dateCached: String? = null,

//
//    @PrimaryKey @field:SerializedName("id") val id: Int,
//    @field:SerializedName("title") val title: String?,
//    @field:SerializedName("publisher") val publisher: String?,
//    @field:SerializedName("featuredImage") val featuredImage: String?,
//    @field:SerializedName("rating") val rating: Int,
//    @field:SerializedName("source_url") val source_url: String?,
//    @field:SerializedName("description") val description: String?,
//    @field:SerializedName("cookingInstructions") val cookingInstructions: String?,
//    @field:SerializedName("ingredients") val ingredients: String,
//    @field:SerializedName("dateAdded") val dateAdded: String?,
//    @field:SerializedName("dateUpdated") val dateUpdated: String?,
//    @field:SerializedName("dateCached") val dateCached: String?,




)
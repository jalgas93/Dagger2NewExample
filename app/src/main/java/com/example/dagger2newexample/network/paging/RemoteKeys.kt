package com.example.dagger2newexample.network.paging

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "remote_keys")
data class RemoteKeys (


    @PrimaryKey @field:SerializedName("repoId") val repoId: Int,
    @field:SerializedName("prevKey") val prevKey: Int?,
    @field:SerializedName("nextKey") val nextKey: Int?



        )
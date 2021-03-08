package com.example.dagger2newexample.network.paging

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys (
    @PrimaryKey
    val repoId:Long,
    val prevKey:Int?,
    val nextKey: Int?
        )
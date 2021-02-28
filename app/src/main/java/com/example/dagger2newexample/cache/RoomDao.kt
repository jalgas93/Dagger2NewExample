package com.example.dagger2newexample.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dagger2newexample.cache.model.RoomModel

@Dao
interface RoomDao {

    @Insert
    suspend fun insert(roomModel: RoomModel):Long

    @Query("SELECT * FROM tableNameFood WHERE id = :id")
    suspend fun getRecipeById(id: Int): List<RoomModel>

}
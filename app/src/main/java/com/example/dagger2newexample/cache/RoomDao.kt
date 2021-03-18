package com.example.dagger2newexample.cache

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dagger2newexample.cache.model.RoomModel
import com.example.dagger2newexample.network.RetrofitModel

@Dao
interface RoomDao {

    @Insert
    suspend fun insert(roomModel: RoomModel): Long

    @Query("SELECT * FROM tableNameFood WHERE id = :id")
    suspend fun getRecipeById(id: Int): List<RoomModel>

    @Query("SELECT * FROM tableNameFood WHERE " +
            "title LIKE :queryString OR description LIKE :queryString " +
            "ORDER BY rating DESC, title ASC")
    fun foodByName(queryString: String): PagingSource<Int, RoomModel>

    //Очистить все данные в таблице.
    @Query("DELETE from tableNameFood")
    suspend fun clearRetrofitModel()

    @Insert
    suspend fun insertFood(retrofitModel: List<RoomModel>)


//    @Query("SELECT * FROM tableNameFood")
//    fun getFood(): PagingSource<Int, RoomModel>

}
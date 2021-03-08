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


    @Query(
        "SELECT * FROM tableNameFood WHERE " + "name LIKE : queryString OR description LIKE :queryString"
                + "ORDER BY stars DESC, name ASC "
    )
    fun foodByName(queryString: String): PagingSource<Int, RetrofitModel>

    //Очистить все данные в таблице.
    @Query("DELETE from tableNameFood")
    suspend fun clearRetrofitModel()

    @Insert
    suspend fun insertFood(retrofitModel: List<RetrofitModel>)

}
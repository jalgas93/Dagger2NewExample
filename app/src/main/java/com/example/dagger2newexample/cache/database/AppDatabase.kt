package com.example.dagger2newexample.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dagger2newexample.cache.RoomDao
import com.example.dagger2newexample.cache.model.RemoteKeysDao
import com.example.dagger2newexample.cache.model.RoomModel
import com.example.dagger2newexample.model.Model

import com.example.dagger2newexample.network.RetrofitModel
import com.example.dagger2newexample.network.paging.RemoteKeys

@Database(entities = [RoomModel::class,RemoteKeys::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao
    abstract fun remoteKeysDao():RemoteKeysDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance?: buildDatabase(context).also { instance = it }
            }
        }


        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "RoomDatabase")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    //    val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                    //    WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }
}
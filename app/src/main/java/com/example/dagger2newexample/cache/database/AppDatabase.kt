package com.example.dagger2newexample.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dagger2newexample.cache.RoomDao
import com.example.dagger2newexample.model.RetrofitModel

@Database(entities = [RetrofitModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao

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
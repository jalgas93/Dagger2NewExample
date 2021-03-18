package com.example.dagger2newexample.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.dagger2newexample.App
import com.example.dagger2newexample.cache.RoomDao
import com.example.dagger2newexample.cache.database.AppDatabase
import com.example.dagger2newexample.cache.model.RoomMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
//
//    @Singleton
//    @Provides
//    fun provideRoomModule(app: Application) = AppDatabase.getInstance(app)
//
//    @Singleton
//    @Provides
//    fun provideRoomDao(db: AppDatabase) = db.roomDao()

//
//    @Provides
//    @Singleton
//    fun provideDatabase(context: Context) = Room
//        .databaseBuilder(context, AppDatabase::class.java, "RoomDatabase")
//        .fallbackToDestructiveMigration()
//        .build()
//
    @Singleton
    @Provides
       fun provideRoomMapper():RoomMapper{
           return RoomMapper()
       }






//    @Singleton
//    @Provides
//    fun provideContext(application: App): Context = application

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): AppDatabase {
        val databaseName = "book_database"
        var Instance: AppDatabase? = null

        if (Instance == null) {
            synchronized(AppDatabase::class.java) {
                Instance = buildDatabase(context, databaseName)
            }
        }
        return Instance!!
    }

    @Singleton
    @Provides
    fun provideRoomDao(database: AppDatabase): RoomDao = database.roomDao()

//    @Singleton
//    @Provides
//    fun provideBookRepository(dao: RoomDao): BookRepository = LocalBookRepository(dao)

    private fun buildDatabase(context: Context, databaseName: String): AppDatabase {
        return Room.databaseBuilder<AppDatabase>(
            context.applicationContext,
            AppDatabase::class.java,
            databaseName
        ).build()
    }

}
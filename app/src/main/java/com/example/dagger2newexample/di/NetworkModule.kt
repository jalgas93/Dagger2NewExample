package com.example.dagger2newexample.di

import com.example.dagger2newexample.cache.database.AppDatabase
import com.example.dagger2newexample.cache.model.RoomMapper
import com.example.dagger2newexample.network.RetrofitMapper
import com.example.dagger2newexample.network.RetrofitService
import com.example.dagger2newexample.repository.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {


    @Singleton
    @Provides
    fun provideSearchRepository(
        retrofitService: RetrofitService,
        database: AppDatabase,
        roomMapper: RoomMapper,
        retrofitMapper: RetrofitMapper
    ): SearchRepository {
        return SearchRepository(retrofitService,database,roomMapper, retrofitMapper)
    }

    @Singleton
    @Provides
    fun provideRetrofitMapper():RetrofitMapper{
        return RetrofitMapper()
    }
}
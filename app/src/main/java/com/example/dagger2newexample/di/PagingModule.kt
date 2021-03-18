package com.example.dagger2newexample.di

import com.example.dagger2newexample.cache.database.AppDatabase
import com.example.dagger2newexample.cache.model.RoomMapper
import com.example.dagger2newexample.network.RetrofitMapper
import com.example.dagger2newexample.network.RetrofitService

import com.example.dagger2newexample.network.paging.RecipeRemoteMediator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PagingModule {

//    @Singleton
//    @Provides
//    fun provideGitHubPagingSource(query:String,retrofitService: RetrofitService)
//    = GitHubPagingSourse(query,retrofitService)


    @Singleton
    @Provides
    fun provideRemoteMediator(query:String,retrofitService: RetrofitService,roomDatabase:AppDatabase,
                              roomMapper: RoomMapper,retrofitMapper: RetrofitMapper)
            = RecipeRemoteMediator(query, retrofitService, roomDatabase, roomMapper, retrofitMapper)
}
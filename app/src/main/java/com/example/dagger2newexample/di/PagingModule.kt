package com.example.dagger2newexample.di

import com.example.dagger2newexample.network.RetrofitService
import com.example.dagger2newexample.network.paging.GitHubPagingSourse
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PagingModule {

    @Singleton
    @Provides
    fun provideGitHubPagingSource(query:String,retrofitService: RetrofitService) = GitHubPagingSourse(query,retrofitService)
}
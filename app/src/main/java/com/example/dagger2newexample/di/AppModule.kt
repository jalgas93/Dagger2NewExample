package com.example.dagger2newexample.di

import android.app.Application
import android.content.Context
import com.example.dagger2newexample.App
import com.example.dagger2newexample.cache.database.AppDatabase
import com.example.dagger2newexample.network.RetrofitService
import com.example.dagger2newexample.repository.Repository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

//    @Provides
//    @Singleton
//    fun provideApplication() = application
//
//    @Provides
//    @Singleton
//    fun provideContext(): Context = application.applicationContext



    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {

        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

    }

    @Singleton
    @Provides
    fun providesRepository(retrofitService: RetrofitService)= Repository(
        retrofitService
    )


    @Provides
    @Singleton
    fun providesGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providesConvertGsonFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun providesRetrofitService(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okHttpClient, converterFactory, RetrofitService::class.java)
    // @CoroutineScropeIO
    @Provides
    fun coroutineScopeIO() = CoroutineScope(Dispatchers.IO)
    private fun createRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitService.ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
   }

    @Singleton
    @Provides
    @Named("auth_token")
    fun provideToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }

    private fun <T> provideService(
        okHttpClient: OkHttpClient, converterFactory: GsonConverterFactory, clazz: Class<T>
    ): T {
        return createRetrofit(okHttpClient, converterFactory).create(clazz)
    }
}
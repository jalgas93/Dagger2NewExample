package com.example.dagger2newexample.di

import android.app.Application
import com.example.dagger2newexample.cache.database.AppDatabase
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRoomModule(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideRoomDao(db: AppDatabase) = db.roomDao()

    @Provides
    @Singleton
    fun providesOkHttpClient() : OkHttpClient {

        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

    }
}
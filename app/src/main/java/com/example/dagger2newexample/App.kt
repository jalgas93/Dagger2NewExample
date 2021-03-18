package com.example.dagger2newexample

import android.app.Application
import com.example.dagger2newexample.di.AppComponent
import com.example.dagger2newexample.di.AppModule
import com.example.dagger2newexample.di.DaggerAppComponent
import com.example.dagger2newexample.di.DatabaseModule


class App : Application() {
    lateinit var appComponent: AppComponent

    //lazy -> Мы делаем это с помощью ленивой инициализации Kotlin, чтобы переменная была неизменной и инициализировалась только при необходимости.

    override fun onCreate() {
        super.onCreate()
        appComponent = createComponent()
    }

    open fun createComponent(): AppComponent {
      return  DaggerAppComponent.builder().bindContext(
            this
        ).build()
    }

    fun getComponent():AppComponent{
        return appComponent
    }


}
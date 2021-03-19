package com.example.dagger2newexample

import android.app.Application
import com.example.dagger2newexample.di.AppComponent
import com.example.dagger2newexample.di.AppModule
import com.example.dagger2newexample.di.DaggerAppComponent
import com.example.dagger2newexample.di.DatabaseModule


class App : Application() {

    //lazy -> Мы делаем это с помощью ленивой инициализации Kotlin, чтобы переменная была неизменной и инициализировалась только при необходимости.

    // Создает экземпляр AppComponent, используя его конструктор Factory
    // Мы передаем applicationContext, который будет использоваться как Context на графике

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    // где спрашивають context  вот отсюда передаем через App


}
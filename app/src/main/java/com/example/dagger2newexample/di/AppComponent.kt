package com.example.dagger2newexample.di

import android.app.Application
import android.content.Context
import com.example.dagger2newexample.App
import com.example.dagger2newexample.presentations.detailFragment.DetailFragment
import com.example.dagger2newexample.presentations.mainFragment.MainFragment
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

//Для AppComponent, мы можем использовать @Singletonаннотацию области видимости, которая является единственной аннотацией области, которая поставляется с javax.injectпакетом. Если мы аннотируем компонент
//с помощью @Singleton, все классы, также аннотированные с помощью, @Singletonбудут ограничены его временем жизни.
@Singleton
@Component(modules = [  NetworkModule::class,AppModule::class])
interface AppComponent  {

    //@BindsInstatce  , @Component.Factory   sposob peredat context
    //Мы объявляем интерфейс с аннотацией @Component.Factory. Внутри есть метод, который возвращает тип компонента (т.е. AppComponent) и имеет параметр типа, Contextпомеченный знаком @BindsInstance.
    //@BindsInstanceсообщает Dagger, что ему необходимо добавить этот экземпляр в граф и, когда Contextпотребуется, предоставить этот экземпляр
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailFragment)
}
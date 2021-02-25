package com.example.dagger2newexample.di

import com.example.dagger2newexample.presentations.mainFragment.MainFragment
import dagger.Component
import javax.inject.Singleton

@Component
interface AppComponent {
    fun inject(fragment:MainFragment)
}
package ru.miwas.winediary.homelist.di.module

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import ru.miwas.winediary.di.annotations.ScreenScope
import ru.miwas.winediary.homelist.HomeListFragment

@Module
class HomeListFragmentModule(private val fragment: Fragment) {

    @ScreenScope
    @Provides
    fun provideFragment() = fragment

    @ScreenScope
    @Provides
    fun provideHomeListFragment() = fragment as HomeListFragment
}
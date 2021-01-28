package ru.miwas.winediary.di.modules

import dagger.Module
import dagger.Provides
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.navigationcore.FragmentNavigationHelperImpl
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun provideFragmentNavigationHelper(): FragmentNavigationHelper = FragmentNavigationHelperImpl()
}
package ru.miwas.winediary.homelist.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.miwas.winediary.di.annotations.ScreenScope
import ru.miwas.winediary.di.annotations.ViewModelKey
import ru.miwas.winediary.di.modules.ViewModelModule
import ru.miwas.winediary.homelist.HomeListFragment
import ru.miwas.winediary.homelist.HomeListViewModel
import ru.miwas.winediary.homelist.HomeListViewModelImpl
import ru.miwas.winediary.homelist.navigation.HomeListNavigator
import ru.miwas.winediary.homelist.navigation.HomeListNavigatorImpl

@Module(includes = [HomeListModule.BindingModule::class])
class HomeListModule {

    @ScreenScope
    @Provides
    fun provideViewModel(
        factory: ViewModelProvider.Factory,
        fragment: HomeListFragment
    ): HomeListViewModel =
        ViewModelProvider(
            fragment,
            factory
        )[HomeListViewModelImpl::class.java]

    @Module(includes = [ViewModelModule::class])
    interface BindingModule {

        @Binds
        @IntoMap
        @ViewModelKey(HomeListViewModelImpl::class)
        fun bindHomeListViewModel(viewModel: HomeListViewModelImpl): ViewModel

        @Binds
        @ScreenScope
        fun bindHomeListNavigator(navigatorImpl: HomeListNavigatorImpl): HomeListNavigator
    }
}
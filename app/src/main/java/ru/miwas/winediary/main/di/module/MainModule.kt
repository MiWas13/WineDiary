package ru.miwas.winediary.main.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.miwas.winediary.di.annotations.ActivityScope
import ru.miwas.winediary.di.annotations.ViewModelKey
import ru.miwas.winediary.di.modules.ViewModelModule
import ru.miwas.winediary.main.MainActivity
import ru.miwas.winediary.main.MainViewModel
import ru.miwas.winediary.main.MainViewModelImpl
import ru.miwas.winediary.main.navigation.MainNavigator
import ru.miwas.winediary.main.navigation.MainNavigatorImpl

@Module(includes = [MainModule.BindingModule::class])
class MainModule {

    @ActivityScope
    @Provides
    fun provideViewModel(
        factory: ViewModelProvider.Factory,
        activity: MainActivity
    ): MainViewModel =
        ViewModelProvider(
            activity,
            factory
        )[MainViewModelImpl::class.java]

    @Module(includes = [ViewModelModule::class])
    interface BindingModule {

        @Binds
        @IntoMap
        @ViewModelKey(MainViewModelImpl::class)
        fun bindMainViewModel(viewModel: MainViewModelImpl): ViewModel

        @Binds
        @ActivityScope
        fun bindMainNavigator(navigatorImpl: MainNavigatorImpl): MainNavigator
    }
}
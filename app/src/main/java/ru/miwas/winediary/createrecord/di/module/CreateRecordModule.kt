package ru.miwas.winediary.createrecord.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.miwas.winediary.createrecord.CreateRecordFragment
import ru.miwas.winediary.createrecord.CreateRecordViewModel
import ru.miwas.winediary.createrecord.CreateRecordViewModelImpl
import ru.miwas.winediary.createrecord.navigation.CreateRecordNavigator
import ru.miwas.winediary.createrecord.navigation.CreateRecordNavigatorImpl
import ru.miwas.winediary.di.annotations.ScreenScope
import ru.miwas.winediary.di.annotations.ViewModelKey
import ru.miwas.winediary.di.modules.ViewModelModule

@Module(includes = [CreateRecordModule.BindingModule::class])
internal class CreateRecordModule {

    @ScreenScope
    @Provides
    fun provideViewModel(
        factory: ViewModelProvider.Factory,
        fragment: CreateRecordFragment
    ): CreateRecordViewModel =
        ViewModelProvider(
            fragment,
            factory
        )[CreateRecordViewModelImpl::class.java]

    @Module(includes = [ViewModelModule::class])
    interface BindingModule {

        @Binds
        @IntoMap
        @ViewModelKey(CreateRecordViewModelImpl::class)
        fun bindCreateRecordViewModel(viewModel: CreateRecordViewModelImpl): ViewModel

        @Binds
        @ScreenScope
        fun bindCreateRecordNavigator(navigatorImpl: CreateRecordNavigatorImpl): CreateRecordNavigator
    }
}
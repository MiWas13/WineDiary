package ru.miwas.winediary.record.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.miwas.winediary.di.annotations.ScreenScope
import ru.miwas.winediary.di.annotations.ViewModelKey
import ru.miwas.winediary.di.modules.ViewModelModule
import ru.miwas.winediary.record.RecordFragment
import ru.miwas.winediary.record.RecordViewModel
import ru.miwas.winediary.record.RecordViewModelImpl
import ru.miwas.winediary.record.navigation.RecordNavigator
import ru.miwas.winediary.record.navigation.RecordNavigatorImpl

@Module(includes = [RecordModule.BindingModule::class])
class RecordModule {

    @ScreenScope
    @Provides
    fun provideViewModel(
        factory: ViewModelProvider.Factory,
        fragment: RecordFragment
    ): RecordViewModel =
        ViewModelProvider(
            fragment,
            factory
        )[RecordViewModelImpl::class.java]

    @Module(includes = [ViewModelModule::class])
    interface BindingModule {

        @Binds
        @IntoMap
        @ViewModelKey(RecordViewModelImpl::class)
        fun bindRecordViewModel(viewModel: RecordViewModelImpl): ViewModel

        @Binds
        @ScreenScope
        fun bindRecordNavigator(navigatorImpl: RecordNavigatorImpl): RecordNavigator
    }
}
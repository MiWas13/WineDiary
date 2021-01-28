package ru.miwas.winediary.stubmain.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.miwas.winediary.di.annotations.ScreenScope
import ru.miwas.winediary.di.annotations.ViewModelKey
import ru.miwas.winediary.di.modules.ViewModelModule
import ru.miwas.winediary.stubmain.StubMainFragment
import ru.miwas.winediary.stubmain.StubMainViewModel
import ru.miwas.winediary.stubmain.StubMainViewModelImpl
import ru.miwas.winediary.stubmain.navigation.StubMainNavigator
import ru.miwas.winediary.stubmain.navigation.StubMainNavigatorImpl

@Module(includes = [StubMainModule.BindingModule::class])
class StubMainModule {

    @ScreenScope
    @Provides
    fun provideViewModel(
        factory: ViewModelProvider.Factory,
        fragment: StubMainFragment
    ): StubMainViewModel =
        ViewModelProvider(
            fragment,
            factory
        )[StubMainViewModelImpl::class.java]

    @Module(includes = [ViewModelModule::class])
    interface BindingModule {

        @Binds
        @IntoMap
        @ViewModelKey(StubMainViewModelImpl::class)
        fun bindStubMainViewModel(viewModel: StubMainViewModelImpl): ViewModel

        @Binds
        @ScreenScope
        fun bindStubMainNavigator(navigatorImpl: StubMainNavigatorImpl): StubMainNavigator
    }
}
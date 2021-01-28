package ru.miwas.winediary.stubmain.di.module;

import androidx.fragment.app.Fragment
import dagger.Module;
import dagger.Provides;
import ru.miwas.winediary.di.annotations.ScreenScope;
import ru.miwas.winediary.stubmain.StubMainFragment

@Module
internal class StubMainFragmentModule(private val fragment: Fragment) {

    @ScreenScope
    @Provides
    fun provideFragment() = fragment

    @ScreenScope
    @Provides
    fun provideStubMainFragment() = fragment as StubMainFragment
}

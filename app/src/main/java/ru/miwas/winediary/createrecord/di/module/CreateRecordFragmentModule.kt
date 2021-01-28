package ru.miwas.winediary.createrecord.di.module

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import ru.miwas.winediary.createrecord.CreateRecordFragment
import ru.miwas.winediary.di.annotations.ScreenScope

@Module
internal class CreateRecordFragmentModule(private val fragment: Fragment) {

    @ScreenScope
    @Provides
    fun provideFragment() = fragment

    @ScreenScope
    @Provides
    fun provideCreateRecordFragment() = fragment as CreateRecordFragment
}
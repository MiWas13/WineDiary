package ru.miwas.winediary.record.di.module

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import ru.miwas.winediary.di.annotations.ScreenScope
import ru.miwas.winediary.record.RecordFragment

@Module
class RecordFragmentModule(private val fragment: Fragment) {

    @ScreenScope
    @Provides
    fun provideFragment() = fragment

    @ScreenScope
    @Provides
    fun provideRecordFragment() = fragment as RecordFragment
}
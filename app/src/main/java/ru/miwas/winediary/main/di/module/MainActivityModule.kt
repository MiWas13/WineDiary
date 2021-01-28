package ru.miwas.winediary.main.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import ru.miwas.winediary.di.annotations.ActivityScope
import ru.miwas.winediary.main.MainActivity

@Module
class MainActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun provideActivity() = activity

    @ActivityScope
    @Provides
    fun provideMainActivity() = activity as MainActivity
}
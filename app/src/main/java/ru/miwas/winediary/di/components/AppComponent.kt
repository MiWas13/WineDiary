package ru.miwas.winediary.di.components

import dagger.Component
import ru.miwas.winediary.appmetrica.AppMetricaSender
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.di.modules.AppMetricaModule
import ru.miwas.winediary.di.modules.DatabaseModule
import ru.miwas.winediary.di.modules.NavigationModule
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppMetricaModule::class,
        DatabaseModule::class,
        NavigationModule::class
    ]
)
interface AppComponent {

    fun provideDatabase(): AppDatabase

    fun provideAppMetrica(): AppMetricaSender

    fun provideFragmentNavigationHelper(): FragmentNavigationHelper

}
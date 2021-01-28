package ru.miwas.winediary.di.modules

import dagger.Module
import dagger.Provides
import ru.miwas.winediary.appmetrica.AppMetricaSender
import ru.miwas.winediary.appmetrica.AppMetricaSenderImpl
import javax.inject.Singleton

@Module
class AppMetricaModule {

    @Provides
    @Singleton
    fun provideAppMetrica(): AppMetricaSender = AppMetricaSenderImpl()
}
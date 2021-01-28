package ru.miwas.winediary.base

import android.app.Application
import androidx.room.Room
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import ru.miwas.winediary.appmetrica.AppMetricaSender
import ru.miwas.winediary.appmetrica.AppMetricaSenderImpl
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.di.DaggerDI
import ru.miwas.winediary.di.components.DaggerAppComponent
import ru.miwas.winediary.di.modules.DatabaseModule
import ru.miwas.winediary.utils.Constants.DATABASE_NAME
import ru.miwas.winediary.utils.Constants.YANDEX_APP_METRICA_KEY

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initAppMetrica()

        DaggerDI.appComponent =
            DaggerAppComponent
                .builder()
                .databaseModule(DatabaseModule(applicationContext))
                .build()
    }

    private fun initAppMetrica() {
        val config = YandexMetricaConfig.newConfigBuilder(YANDEX_APP_METRICA_KEY).build()
        YandexMetrica.activate(applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this)
    }

    companion object {
        lateinit var instance: App
    }
}
package ru.miwas.winediary.base

import android.app.Application
import androidx.room.Room
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import ru.miwas.winediary.appmetrica.AppMetricaSender
import ru.miwas.winediary.appmetrica.AppMetricaSenderImpl
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.utils.Constants.DATABASE_NAME
import ru.miwas.winediary.utils.Constants.YANDEX_APP_METRICA_KEY

class App : Application() {

    lateinit var database: AppDatabase
    lateinit var appMetricaSender: AppMetricaSender

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME).build()
        appMetricaSender = AppMetricaSenderImpl()
        initAppMetrica()
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
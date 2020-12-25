package ru.miwas.winediary.base

import android.app.Application
import androidx.room.Room
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.utils.Constants.DATABASE_NAME

class App : Application() {

    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME).build()
    }

    companion object {
        lateinit var instance: App
    }
}
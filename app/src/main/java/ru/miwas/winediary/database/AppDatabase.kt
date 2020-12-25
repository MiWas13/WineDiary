package ru.miwas.winediary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.miwas.winediary.database.model.WineEntity

@Database(entities = [WineEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wineDao(): WineDao
}
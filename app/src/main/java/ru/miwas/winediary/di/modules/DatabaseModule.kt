package ru.miwas.winediary.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.utils.Constants
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME)
        .build()
}
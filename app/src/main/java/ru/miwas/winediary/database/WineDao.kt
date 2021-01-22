package ru.miwas.winediary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.miwas.winediary.database.model.WineEntity
import ru.miwas.winediary.utils.Constants.WINE_TABLE_NAME
import ru.miwas.winediary.utils.Constants.SPACE

@Dao
interface WineDao {

    @Query("SELECT * FROM$SPACE$WINE_TABLE_NAME")
    fun getAllWines(): LiveData<List<WineEntity>>

    @Query("SELECT id, name, image_path, rate_total FROM$SPACE$WINE_TABLE_NAME")
    suspend fun getAllWinesLight(): List<WineEntity>

    @Query("SELECT * FROM$SPACE$WINE_TABLE_NAME ORDER BY id LIMIT 1")
    suspend fun getFirstWine(): List<WineEntity>

    @Query("SELECT * FROM$SPACE$WINE_TABLE_NAME WHERE id = :id")
    suspend fun getWineById(id: Long): WineEntity

    @Insert
    suspend fun insert(wine: WineEntity)

    @Update
    fun update(wine: WineEntity)

    @Delete
    fun delete(wine: WineEntity)

}
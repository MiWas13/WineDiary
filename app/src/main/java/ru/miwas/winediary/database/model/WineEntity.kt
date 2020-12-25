package ru.miwas.winediary.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.miwas.winediary.utils.Constants

@Entity(tableName = Constants.WINE_TABLE_NAME)
data class WineEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String?,
    val country: String?,
    val year: Int?,
    @ColumnInfo(name = "alcohol_percentage")
    val alcoholPercentage: Int?,
    val color: String?,
    val price: Int?,
    @ColumnInfo(name = "image_path")
    val imagePath: String?,
    @ColumnInfo(name = "grape_variety")
    val grapeVariety: String?,
    val smell: String?,
    val taste: String?,
    val combination: String?,
    val notes: String?,
    @ColumnInfo(name = "rate_smell")
    val rateSmell: Int?,
    @ColumnInfo(name = "rate_taste")
    val rateTaste: Int?,
    @ColumnInfo(name = "rate_total")
    val rateTotal: Int?
)

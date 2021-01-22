package ru.miwas.winediary.record.model

data class Wine(
    val name: String,
    val country: String,
    val year: Int,
    val alcoholPercentage: Float,
    val color: String,
    val price: Int,
    val imagePath: String,
    val grapeVariety: String,
    val smell: String,
    val taste: String,
    val combination: String,
    val notes: String,
    val rateSmell: Int,
    val rateTaste: Int,
    val rateTotal: Int
)
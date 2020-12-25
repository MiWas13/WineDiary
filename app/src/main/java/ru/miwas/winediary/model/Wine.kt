package ru.miwas.winediary.model

import java.time.Year

data class Wine(
    val name: String,
    val country: String,
    val year: Year,
    val alcoholPercentage: Int,
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
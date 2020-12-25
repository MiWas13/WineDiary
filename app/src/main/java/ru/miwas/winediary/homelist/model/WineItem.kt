package ru.miwas.winediary.homelist.model

data class WineItem(
    val id: Long,
    val name: String,
    val imagePath: String? = null,
    val rateTotal: Int
)

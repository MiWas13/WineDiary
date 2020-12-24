package ru.miwas.winediary.base

interface BaseViewModel<T> {

    fun startProcesses()

    fun dispatchEvent(event: T)

    fun finishProcesses()
}
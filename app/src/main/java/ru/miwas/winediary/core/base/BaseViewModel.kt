package ru.miwas.winediary.core.base

interface BaseViewModel<T> {

    fun startProcesses()

    fun dispatchEvent(event: T)

    fun finishProcesses()
}
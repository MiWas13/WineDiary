package ru.miwas.winediary.appmetrica

interface AppMetricaSender {

    fun sendEvent(event: String)

    fun sendEventWithParams(event: String, eventParams: String)
}
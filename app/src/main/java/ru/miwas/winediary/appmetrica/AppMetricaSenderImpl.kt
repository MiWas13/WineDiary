package ru.miwas.winediary.appmetrica

import com.yandex.metrica.YandexMetrica

class AppMetricaSenderImpl : AppMetricaSender {

    override fun sendEvent(event: String) {
        YandexMetrica.reportEvent(event)
    }

    override fun sendEventWithParams(event: String, eventParams: String) {
        YandexMetrica.reportEvent(event, eventParams)
    }

}
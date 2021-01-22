package ru.miwas.winediary.record

import androidx.lifecycle.MutableLiveData
import ru.miwas.winediary.base.BaseViewModel
import ru.miwas.winediary.record.model.Wine

interface RecordViewModel : BaseViewModel<RecordViewModel.Event> {

    val wine: MutableLiveData<Wine>

    fun setWineId(id: Long)

    sealed class Event {

    }
}
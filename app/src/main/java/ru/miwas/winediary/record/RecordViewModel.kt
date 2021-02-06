package ru.miwas.winediary.record

import androidx.lifecycle.MutableLiveData
import ru.miwas.winediary.base.BaseViewModel
import ru.miwas.winediary.record.model.Wine

interface RecordViewModel : BaseViewModel<RecordViewModel.Event> {

    val wine: MutableLiveData<Wine>

    val deleteConfirmationDialogState: MutableLiveData<Boolean>

    fun setWineId(id: Long)

    sealed class Event {

        object BackButtonClicked : Event()

        object DeleteButtonClicked : Event()

        object DeleteConfirmationButtonClicked : Event()
    }
}
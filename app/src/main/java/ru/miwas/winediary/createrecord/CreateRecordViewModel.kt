package ru.miwas.winediary.createrecord

import androidx.lifecycle.MutableLiveData
import ru.miwas.winediary.base.BaseViewModel

interface CreateRecordViewModel : BaseViewModel<CreateRecordViewModel.Event> {

    val viewPagerActivePage: MutableLiveData<Int>
    val choosePhoto: MutableLiveData<Boolean>

    sealed class Event {

        object OnImageClicked : Event()

        class OnImageSelected(val imageUri: String) : Event()

        object PreviousStepClicked : Event()

        object NextStepClicked : Event()

        object ConfirmClicked : Event()

        object ToMainClicked : Event()

        class OnEditTextChanged(val editTextType: EditTextType) : Event()

        class OnRatingBarClicked(val ratingBarType: RatingBarType) : Event()
    }

    sealed class EditTextType {
        class Name(val text: String?) : EditTextType()
        class Country(val text: String?) : EditTextType()
        class Year(val text: String?) : EditTextType()
        class AlcoholPercentage(val text: String?) : EditTextType()
        class Color(val text: String?) : EditTextType()
        class Price(val text: String?) : EditTextType()
        class GrapeVarieties(val text: String?) : EditTextType()
        class SmellDescription(val text: String?) : EditTextType()
        class TasteDescription(val text: String?) : EditTextType()
        class Combination(val text: String?) : EditTextType()
        class Notes(val text: String?) : EditTextType()
    }

    sealed class RatingBarType {
        class Smell(val rating: Float) : RatingBarType()
        class Taste(val rating: Float) : RatingBarType()
        class Total(val rating: Float) : RatingBarType()
    }
}
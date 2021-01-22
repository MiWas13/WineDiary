package ru.miwas.winediary.createrecord

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miwas.winediary.appmetrica.AppMetricaSender
import ru.miwas.winediary.base.App
import ru.miwas.winediary.createrecord.navigation.CreateRecordNavigator
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.NextStepClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.ConfirmClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.ToMainClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.OnEditTextChanged
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.OnRatingBarClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.OnImageClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.OnImageSelected
import ru.miwas.winediary.createrecord.CreateRecordViewModel.RatingBarType
import ru.miwas.winediary.createrecord.CreateRecordViewModel.RatingBarType.Smell
import ru.miwas.winediary.createrecord.CreateRecordViewModel.RatingBarType.Taste
import ru.miwas.winediary.createrecord.CreateRecordViewModel.RatingBarType.Total
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Name
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Country
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Year
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.AlcoholPercentage
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Color
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Price
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.GrapeVarieties
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.SmellDescription
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.TasteDescription
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Combination
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Notes
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.database.model.WineEntity
import ru.miwas.winediary.utils.Constants.EMPTY_STRING

class CreateRecordViewModelImpl(
    private val createRecordNavigator: CreateRecordNavigator
) : CreateRecordViewModel, ViewModel() {

    override val viewPagerActivePage: MutableLiveData<Int> = MutableLiveData()
    override val choosePhoto: MutableLiveData<Boolean> = MutableLiveData()
    private val database: AppDatabase = App.instance.database
    private val appMetricaSender: AppMetricaSender = App.instance.appMetricaSender

    private var name: String = EMPTY_STRING
    private var country: String = EMPTY_STRING
    private var year: Int = 0
    private var alcoholPercentage: Float = 0F
    private var color: String = EMPTY_STRING
    private var price: Int = 0
    private var grapeVarieties: String = EMPTY_STRING
    private var smell: String = EMPTY_STRING
    private var taste: String = EMPTY_STRING
    private var combination: String = EMPTY_STRING
    private var notes: String = EMPTY_STRING
    private var imagePath: String = EMPTY_STRING
    private var smellRating: Int = 0
    private var tasteRating: Int = 0
    private var totalRating: Int = 0

    override fun startProcesses() {
        appMetricaSender.sendEvent(EVENT_SHOW_CREATE_RECORD)
        viewPagerActivePage.value = 0
    }

    override fun dispatchEvent(event: CreateRecordViewModel.Event) {
        when (event) {
            NextStepClicked -> {
                appMetricaSender.sendEvent(EVENT_CLICK_NEXT)
                checkValuesAndUpdate(false)
            }
            ConfirmClicked -> {
                appMetricaSender.sendEvent(EVENT_CLICK_CONFIRM)
                checkValuesAndUpdate(true)
            }
            ToMainClicked -> {
                appMetricaSender.sendEvent(EVENT_CLICK_GO_TO_MAIN)
                finish()
            }
            is OnEditTextChanged -> editTextChanged(event.editTextType)
            is OnRatingBarClicked -> ratingChanged(event.ratingBarType)
            OnImageClicked -> choosePhotoClicked()
            is OnImageSelected -> photoChosen(event.imageUri)
        }
    }

    override fun finishProcesses() {
        createRecordNavigator.clear()
    }

    private fun checkValuesAndUpdate(isSaving: Boolean) {
        viewPagerActivePage.value = viewPagerActivePage.value?.plus(1)

        if (isSaving) {
//            val wineEntity = WineEntity(
//                name = name,
//                country = country,
//                year = year,
//                alcoholPercentage = alcoholPercentage,
//                color = color,
//                price = price,
//                grapeVariety = grapeVarieties,
//                smell = smell,
//                taste = taste,
//                combination = combination,
//                notes = notes,
//                rateSmell = smellRating,
//                rateTaste = tasteRating,
//                rateTotal = totalRating,
//                imagePath = imageUri
//            )
            val wineEntity = WineEntity(
                name = "Крутое вино",
                country = "Австралия",
                year = 2013,
                alcoholPercentage = 13.4F,
                color = "Красное",
                price = 1500,
                grapeVariety = "Крутой виноградик",
                smell = "Вкусно пахнет",
                taste = "На вкус вкусно",
                combination = "С сыром",
                notes = "Полезная заметка",
                rateSmell = 4,
                rateTaste = 5,
                rateTotal = 4,
                imagePath = imagePath
            )
            viewModelScope.launch {
                database.wineDao().insert(wineEntity)
            }
        }
    }

    private fun validateFirstStep() {

    }

    private fun validateSecondStep() {

    }

    private fun editTextChanged(editTextType: EditTextType) {
        when (editTextType) {
            is Name -> name = editTextType.text ?: EMPTY_STRING
            is Country -> country = editTextType.text ?: EMPTY_STRING
            is Year -> year = editTextType.text?.toInt() ?: 0
            is AlcoholPercentage -> alcoholPercentage = editTextType.text?.toFloat() ?: 0F
            is Color -> color = editTextType.text ?: EMPTY_STRING
            is Price -> price = editTextType.text?.toInt() ?: 0
            is GrapeVarieties -> grapeVarieties = editTextType.text ?: EMPTY_STRING
            is SmellDescription -> smell = editTextType.text ?: EMPTY_STRING
            is TasteDescription -> taste = editTextType.text ?: EMPTY_STRING
            is Combination -> combination = editTextType.text ?: EMPTY_STRING
            is Notes -> notes = editTextType.text ?: EMPTY_STRING
        }
    }

    private fun choosePhotoClicked() {
        choosePhoto.value = true
    }

    private fun photoChosen(photoUri: String) {
        imagePath = photoUri
    }

    private fun ratingChanged(ratingBarType: RatingBarType) {
        when (ratingBarType) {
            is Smell -> smellRating = ratingBarType.rating.toInt()
            is Taste -> tasteRating = ratingBarType.rating.toInt()
            is Total -> totalRating = ratingBarType.rating.toInt()
        }
    }

    private fun finish() {
        createRecordNavigator.goToMainScreen()
    }

    companion object {
        const val EVENT_SHOW_CREATE_RECORD = "Create_record_screen_show"
        const val EVENT_CLICK_NEXT = "Next_button_record_screen_click"
        const val EVENT_CLICK_CONFIRM = "Confirm_button_record_screen_click"
        const val EVENT_CLICK_GO_TO_MAIN = "Go_to_main_button_record_screen_click"
    }
}
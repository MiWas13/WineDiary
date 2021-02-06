package ru.miwas.winediary.createrecord

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miwas.winediary.appmetrica.AppMetricaSender
import ru.miwas.winediary.createrecord.navigation.CreateRecordNavigator
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.PreviousStepClicked
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
import ru.miwas.winediary.utils.Constants.EMPTY_FLOAT
import ru.miwas.winediary.utils.Constants.EMPTY_INT
import ru.miwas.winediary.utils.Constants.EMPTY_STRING
import javax.inject.Inject

class CreateRecordViewModelImpl @Inject constructor(
    private val createRecordNavigator: CreateRecordNavigator,
    private val database: AppDatabase,
    private val appMetricaSender: AppMetricaSender
) : CreateRecordViewModel, ViewModel() {

    override val viewPagerActivePage: MutableLiveData<Int> = MutableLiveData()
    override val choosePhoto: MutableLiveData<Boolean> = MutableLiveData()

    private var name: String = EMPTY_STRING
    private var country: String = EMPTY_STRING
    private var year: Int = EMPTY_INT
    private var alcoholPercentage: Float = EMPTY_FLOAT
    private var color: String = EMPTY_STRING
    private var price: Int = EMPTY_INT
    private var grapeVarieties: String = EMPTY_STRING
    private var smell: String = EMPTY_STRING
    private var taste: String = EMPTY_STRING
    private var combination: String = EMPTY_STRING
    private var notes: String = EMPTY_STRING
    private var imagePath: String = EMPTY_STRING
    private var smellRating: Int = EMPTY_INT
    private var tasteRating: Int = EMPTY_INT
    private var totalRating: Int = EMPTY_INT

    override fun startProcesses() {
        appMetricaSender.sendEvent(EVENT_SHOW_CREATE_RECORD)
        viewPagerActivePage.value = 0
    }

    override fun dispatchEvent(event: CreateRecordViewModel.Event) {
        when (event) {
            PreviousStepClicked -> {
                appMetricaSender.sendEvent(EVENT_CLICK_PREVIOUS)
                backToPreviousStep()
            }
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
            val wineEntity = WineEntity(
                name = name,
                country = country,
                year = year,
                alcoholPercentage = alcoholPercentage,
                color = color,
                price = price,
                grapeVariety = grapeVarieties,
                smell = smell,
                taste = taste,
                combination = combination,
                notes = notes,
                rateSmell = smellRating,
                rateTaste = tasteRating,
                rateTotal = totalRating,
                imagePath = imagePath
            )
//            val wineEntity = WineEntity(
//                name = "Крутое вино",
//                country = "Австралия",
//                year = 2013,
//                alcoholPercentage = 13.4F,
//                color = "Красное",
//                price = 1500,
//                grapeVariety = "Крутой виноградик",
//                smell = "Вкусно пахнет",
//                taste = "На вкус вкусно",
//                combination = "С сыром",
//                notes = "Полезная заметка",
//                rateSmell = 4,
//                rateTaste = 5,
//                rateTotal = 4,
//                imagePath = imagePath
//            )
            viewModelScope.launch {
                database.wineDao().insert(wineEntity)
            }
        }
    }

    private fun backToPreviousStep() {
        viewPagerActivePage.value = viewPagerActivePage.value?.minus(1)
    }

    private fun editTextChanged(editTextType: EditTextType) {
        when (editTextType) {
            is Name -> name = editTextType.text ?: EMPTY_STRING
            is Country -> country = editTextType.text ?: EMPTY_STRING
            is Year -> year = validateIntNumber(editTextType.text)
            is AlcoholPercentage -> alcoholPercentage = validateFloatNumber(editTextType.text)
            is Color -> color = editTextType.text ?: EMPTY_STRING
            is Price -> price = validateIntNumber(editTextType.text)
            is GrapeVarieties -> grapeVarieties = editTextType.text ?: EMPTY_STRING
            is SmellDescription -> smell = editTextType.text ?: EMPTY_STRING
            is TasteDescription -> taste = editTextType.text ?: EMPTY_STRING
            is Combination -> combination = editTextType.text ?: EMPTY_STRING
            is Notes -> notes = editTextType.text ?: EMPTY_STRING
        }
    }

    private fun validateIntNumber(text: String?): Int {
        return if (!text.isNullOrEmpty()) {
            text.toInt()
        } else EMPTY_INT
    }

    private fun validateFloatNumber(text: String?): Float {
        return if (!text.isNullOrEmpty()) {
            text.toFloat()
        } else EMPTY_FLOAT
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
        const val EVENT_CLICK_PREVIOUS = "Previous_button_record_screen_click"
        const val EVENT_CLICK_NEXT = "Next_button_record_screen_click"
        const val EVENT_CLICK_CONFIRM = "Confirm_button_record_screen_click"
        const val EVENT_CLICK_GO_TO_MAIN = "Go_to_main_button_record_screen_click"
    }
}
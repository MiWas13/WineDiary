package ru.miwas.winediary.createrecord.steps.second

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import ru.miwas.winediary.R
import ru.miwas.winediary.core.viewbinding.viewBinding
import ru.miwas.winediary.createrecord.CreateRecordViewModel
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.NextStepClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.PreviousStepClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.GrapeVarieties
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.SmellDescription
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.TasteDescription
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Combination
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Notes
import ru.miwas.winediary.databinding.CreateRecordStepSecondFragmentBinding

class SecondStepFragment(
    private val viewModel: CreateRecordViewModel
) : Fragment(R.layout.create_record_step_second_fragment) {

    private val binding by viewBinding(CreateRecordStepSecondFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
    }

    companion object {
        fun newInstance(
            viewModel: CreateRecordViewModel
        ): SecondStepFragment {
            return SecondStepFragment(viewModel)
        }
    }

    private fun prepareView() {

        binding.previousButton.setOnClickListener {
            viewModel.dispatchEvent(PreviousStepClicked)
        }

        binding.nextButton.setOnClickListener {
            viewModel.dispatchEvent(NextStepClicked)
        }

        binding.grapeVarietiesInputEditText.doAfterTextChanged {
            sendEditTextEvent(GrapeVarieties(it?.toString()))
        }

        binding.smellInputEditText.doAfterTextChanged {
            sendEditTextEvent(SmellDescription(it?.toString()))
        }

        binding.tasteInputEditText.doAfterTextChanged {
            sendEditTextEvent(TasteDescription(it?.toString()))
        }

        binding.combinationInputEditText.doAfterTextChanged {
            sendEditTextEvent(Combination(it?.toString()))
        }

        binding.notesInputEditText.doAfterTextChanged {
            sendEditTextEvent(Notes(it?.toString()))
        }
    }


    private fun sendEditTextEvent(type: CreateRecordViewModel.EditTextType) {
        viewModel.dispatchEvent(CreateRecordViewModel.Event.OnEditTextChanged(type))
    }
}
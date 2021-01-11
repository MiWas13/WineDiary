package ru.miwas.winediary.createrecord.steps.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.create_record_step_second_fragment.*
import kotlinx.android.synthetic.main.create_record_step_second_fragment.nextButton
import ru.miwas.winediary.R
import ru.miwas.winediary.createrecord.CreateRecordViewModel
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.NextStepClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.GrapeVarieties
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.SmellDescription
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.TasteDescription
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Combination
import ru.miwas.winediary.createrecord.CreateRecordViewModel.EditTextType.Notes

class SecondStepFragment(
    private val viewModel: CreateRecordViewModel
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_record_step_second_fragment, container, false)
    }

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
        nextButton.setOnClickListener {
            viewModel.dispatchEvent(NextStepClicked)
        }

        grapeVarietiesInputEditText.doAfterTextChanged {
            sendEditTextEvent(GrapeVarieties(it?.toString()))
        }

        smellInputEditText.doAfterTextChanged {
            sendEditTextEvent(SmellDescription(it?.toString()))
        }

        tasteInputEditText.doAfterTextChanged {
            sendEditTextEvent(TasteDescription(it?.toString()))
        }

        combinationInputEditText.doAfterTextChanged {
            sendEditTextEvent(Combination(it?.toString()))
        }

        notesInputEditText.doAfterTextChanged {
            sendEditTextEvent(Notes(it?.toString()))
        }
    }


    private fun sendEditTextEvent(type: CreateRecordViewModel.EditTextType) {
        viewModel.dispatchEvent(CreateRecordViewModel.Event.OnEditTextChanged(type))
    }
}
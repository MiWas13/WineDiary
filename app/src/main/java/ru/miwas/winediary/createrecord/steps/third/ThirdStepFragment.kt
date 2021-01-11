package ru.miwas.winediary.createrecord.steps.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.create_record_step_third_fragment.*
import ru.miwas.winediary.R
import ru.miwas.winediary.createrecord.CreateRecordViewModel
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.ConfirmClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.OnRatingBarClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.RatingBarType.Smell
import ru.miwas.winediary.createrecord.CreateRecordViewModel.RatingBarType.Taste
import ru.miwas.winediary.createrecord.CreateRecordViewModel.RatingBarType.Total

class ThirdStepFragment(
    private val viewModel: CreateRecordViewModel
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_record_step_third_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
    }

    companion object {
        fun newInstance(viewModel: CreateRecordViewModel): ThirdStepFragment {
            return ThirdStepFragment(viewModel)
        }
    }

    private fun prepareView() {
        confirmButton.setOnClickListener {
            viewModel.dispatchEvent(ConfirmClicked)
        }

        smellRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.dispatchEvent(OnRatingBarClicked(Smell(rating)))
        }

        tasteRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.dispatchEvent(OnRatingBarClicked(Taste(rating)))
        }

        totalRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.dispatchEvent(OnRatingBarClicked(Total(rating)))
        }
    }
}
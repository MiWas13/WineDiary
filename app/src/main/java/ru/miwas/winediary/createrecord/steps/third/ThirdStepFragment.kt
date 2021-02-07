package ru.miwas.winediary.createrecord.steps.third

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.miwas.winediary.R
import ru.miwas.winediary.core.viewbinding.viewBinding
import ru.miwas.winediary.createrecord.CreateRecordViewModel
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.PreviousStepClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.ConfirmClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.OnRatingBarClicked
import ru.miwas.winediary.createrecord.CreateRecordViewModel.RatingBarType.Smell
import ru.miwas.winediary.createrecord.CreateRecordViewModel.RatingBarType.Taste
import ru.miwas.winediary.createrecord.CreateRecordViewModel.RatingBarType.Total
import ru.miwas.winediary.databinding.CreateRecordStepThirdFragmentBinding

class ThirdStepFragment(
    private val viewModel: CreateRecordViewModel
) : Fragment(R.layout.create_record_step_third_fragment) {

    private val binding by viewBinding(CreateRecordStepThirdFragmentBinding::bind)

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

        binding.previousButton.setOnClickListener {
            viewModel.dispatchEvent(PreviousStepClicked)
        }

        binding.confirmButton.setOnClickListener {
            viewModel.dispatchEvent(ConfirmClicked)
        }

        binding.smellRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.dispatchEvent(OnRatingBarClicked(Smell(rating)))
        }

        binding.tasteRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.dispatchEvent(OnRatingBarClicked(Taste(rating)))
        }

        binding.totalRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.dispatchEvent(OnRatingBarClicked(Total(rating)))
        }
    }
}
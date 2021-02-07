package ru.miwas.winediary.createrecord.steps.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.miwas.winediary.R
import ru.miwas.winediary.core.viewbinding.viewBinding
import ru.miwas.winediary.createrecord.CreateRecordViewModel
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.ToMainClicked
import ru.miwas.winediary.databinding.CreateRecordResultFragmentBinding

class ResultFragment(
    private val viewModel: CreateRecordViewModel
) : Fragment(R.layout.create_record_result_fragment) {

    private val binding by viewBinding(CreateRecordResultFragmentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_record_result_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
    }

    private fun prepareView() {
        binding.toMainButton.setOnClickListener {
            viewModel.dispatchEvent(ToMainClicked)
        }
    }

    companion object {
        fun newInstance(viewModel: CreateRecordViewModel): ResultFragment {
            return ResultFragment(viewModel)
        }
    }

}
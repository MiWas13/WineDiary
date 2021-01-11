package ru.miwas.winediary.createrecord.steps.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.create_record_result_fragment.*
import ru.miwas.winediary.R
import ru.miwas.winediary.createrecord.CreateRecordViewModel
import ru.miwas.winediary.createrecord.CreateRecordViewModel.Event.ToMainClicked

class ResultFragment(
    private val viewModel: CreateRecordViewModel
) : Fragment() {

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
        toMainButton.setOnClickListener {
            viewModel.dispatchEvent(ToMainClicked)
        }
    }

    companion object {
        fun newInstance(viewModel: CreateRecordViewModel): ResultFragment {
            return ResultFragment(viewModel)
        }
    }

}
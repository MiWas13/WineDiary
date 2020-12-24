package ru.miwas.winediary.createrecord.steps.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.miwas.winediary.R

class ThirdStepFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_record_step_third_fragment, container, false)
    }

    companion object {
        fun newInstance(): ThirdStepFragment {
            return ThirdStepFragment()
        }
    }

    private fun prepareView() {

    }
}
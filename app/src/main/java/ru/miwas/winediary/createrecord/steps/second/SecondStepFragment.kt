package ru.miwas.winediary.createrecord.steps.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.create_record_step_second_fragment.*
import ru.miwas.winediary.R

class SecondStepFragment(private val parentViewPager: ViewPager2) : Fragment() {

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
        fun newInstance(viewPager: ViewPager2): SecondStepFragment {
            return SecondStepFragment(viewPager)
        }
    }

    private fun prepareView() {
        nextButton.setOnClickListener {
            parentViewPager.currentItem = 2
        }
    }
}
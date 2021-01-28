package ru.miwas.winediary.createrecord

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.create_record_fragment.*
import ru.miwas.winediary.R
import ru.miwas.winediary.base.BaseFragment
import ru.miwas.winediary.createrecord.di.component.DaggerCreateRecordComponent
import ru.miwas.winediary.createrecord.di.module.CreateRecordFragmentModule
import ru.miwas.winediary.createrecord.steps.first.FirstStepFragment
import ru.miwas.winediary.createrecord.steps.result.ResultFragment
import ru.miwas.winediary.createrecord.steps.second.SecondStepFragment
import ru.miwas.winediary.createrecord.steps.third.ThirdStepFragment
import ru.miwas.winediary.di.DaggerDI
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import javax.inject.Inject

class CreateRecordFragment : BaseFragment() {

    @Inject
    lateinit var fragmentNavigationHelper: FragmentNavigationHelper

    @Inject
    lateinit var viewModel: CreateRecordViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerCreateRecordComponent
            .builder()
            .createRecordFragmentModule(CreateRecordFragmentModule(this))
            .appComponent(DaggerDI.appComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prepareViewModel()
        return inflater.inflate(R.layout.create_record_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
        observeViewModel()
        viewModel.startProcesses()
    }

    override fun prepareViewModel() {
        fragmentManager?.let {
            fragmentNavigationHelper.configHelper(it, R.id.mainContainer)
        }
    }

    override fun observeViewModel() {
        viewModel.viewPagerActivePage.observe(
            viewLifecycleOwner,
            Observer {
                stepsViewPager.currentItem = it
            }
        )
    }

    override fun prepareView() {
        prepareViewPager()
    }

    private fun prepareViewPager() {
        val stepsFragments = arrayListOf(
            FirstStepFragment.newInstance(viewModel),
            SecondStepFragment.newInstance(viewModel),
            ThirdStepFragment.newInstance(viewModel),
            ResultFragment.newInstance(viewModel)
        )
        activity?.let {
            val adapter = CreateRecordAdapter(it)
            adapter.setSteps(stepsFragments)
            stepsViewPager.adapter = adapter
            stepsViewPager.clipToPadding = false
            stepsViewPager.isUserInputEnabled = false
        }
    }
}
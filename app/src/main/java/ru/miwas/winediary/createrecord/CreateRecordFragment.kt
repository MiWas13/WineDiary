package ru.miwas.winediary.createrecord

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import ru.miwas.winediary.R
import ru.miwas.winediary.core.base.BaseFragment
import ru.miwas.winediary.core.viewbinding.viewBinding
import ru.miwas.winediary.createrecord.di.component.DaggerCreateRecordComponent
import ru.miwas.winediary.createrecord.di.module.CreateRecordFragmentModule
import ru.miwas.winediary.createrecord.steps.first.FirstStepFragment
import ru.miwas.winediary.createrecord.steps.result.ResultFragment
import ru.miwas.winediary.createrecord.steps.second.SecondStepFragment
import ru.miwas.winediary.createrecord.steps.third.ThirdStepFragment
import ru.miwas.winediary.databinding.CreateRecordFragmentBinding
import ru.miwas.winediary.di.DaggerDI
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import javax.inject.Inject

class CreateRecordFragment : BaseFragment(R.layout.create_record_fragment) {

    @Inject
    lateinit var fragmentNavigationHelper: FragmentNavigationHelper

    @Inject
    lateinit var viewModel: CreateRecordViewModel

    private val binding by viewBinding(CreateRecordFragmentBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerCreateRecordComponent
            .builder()
            .createRecordFragmentModule(CreateRecordFragmentModule(this))
            .appComponent(DaggerDI.appComponent)
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareViewModel()
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
                binding.stepsViewPager.currentItem = it
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
            binding.stepsViewPager.adapter = adapter
            binding.stepsViewPager.clipToPadding = false
            binding.stepsViewPager.isUserInputEnabled = false
        }
    }
}
package ru.miwas.winediary.stubmain

import android.content.Context
import android.os.Bundle
import android.view.View
import ru.miwas.winediary.R
import ru.miwas.winediary.core.base.BaseFragment
import ru.miwas.winediary.core.viewbinding.viewBinding
import ru.miwas.winediary.databinding.StubMainFragmentBinding
import ru.miwas.winediary.di.DaggerDI
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.stubmain.StubMainViewModel.Event.AddClicked
import ru.miwas.winediary.stubmain.di.component.DaggerStubMainComponent
import ru.miwas.winediary.stubmain.di.module.StubMainFragmentModule
import javax.inject.Inject

class StubMainFragment : BaseFragment(R.layout.stub_main_fragment) {

    @Inject
    lateinit var fragmentNavigationHelper: FragmentNavigationHelper

    @Inject
    lateinit var viewModel: StubMainViewModel

    private val binding by viewBinding(StubMainFragmentBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerStubMainComponent
            .builder()
            .stubMainFragmentModule(StubMainFragmentModule(this))
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
        addBackPressedCallback {
            activity?.finish()
        }
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

    }

    override fun prepareView() {
        binding.addButton.setOnClickListener {
            viewModel.dispatchEvent(AddClicked)
        }
    }
}
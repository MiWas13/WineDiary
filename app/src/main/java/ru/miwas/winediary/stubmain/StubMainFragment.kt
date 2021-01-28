package ru.miwas.winediary.stubmain

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.stub_main_fragment.addButton
import ru.miwas.winediary.R
import ru.miwas.winediary.base.BaseFragment
import ru.miwas.winediary.di.DaggerDI
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.stubmain.StubMainViewModel.Event.AddClicked
import ru.miwas.winediary.stubmain.di.component.DaggerStubMainComponent
import ru.miwas.winediary.stubmain.di.module.StubMainFragmentModule
import javax.inject.Inject

class StubMainFragment : BaseFragment() {

    @Inject
    lateinit var fragmentNavigationHelper: FragmentNavigationHelper

    @Inject
    lateinit var viewModel: StubMainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerStubMainComponent
            .builder()
            .stubMainFragmentModule(StubMainFragmentModule(this))
            .appComponent(DaggerDI.appComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prepareViewModel()
        return inflater.inflate(R.layout.stub_main_fragment, container, false)
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

    }

    override fun prepareView() {
        addButton.setOnClickListener {
            viewModel.dispatchEvent(AddClicked)
        }
    }
}
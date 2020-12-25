package ru.miwas.winediary.stubmain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.stub_main_fragment.addButton
import ru.miwas.winediary.R
import ru.miwas.winediary.base.BaseFragment
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.navigationcore.FragmentNavigationHelperImpl
import ru.miwas.winediary.stubmain.navigation.StubMainNavigatorImpl
import ru.miwas.winediary.stubmain.StubMainViewModel.Event.AddClicked

class StubMainFragment : BaseFragment() {

    private lateinit var viewModel: StubMainViewModel
    private val fragmentNavigationHelper: FragmentNavigationHelper = FragmentNavigationHelperImpl()

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
        viewModel = StubMainViewModelImpl(StubMainNavigatorImpl(fragmentNavigationHelper))
    }

    override fun observeViewModel() {

    }

    override fun prepareView() {
        addButton.setOnClickListener {
            viewModel.dispatchEvent(AddClicked)
        }
    }
}
package ru.miwas.winediary.homelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.miwas.winediary.R
import ru.miwas.winediary.base.BaseFragment
import ru.miwas.winediary.homelist.navigation.HomeListNavigatorImpl
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.navigationcore.FragmentNavigationHelperImpl

class HomeListFragment : BaseFragment() {

    private lateinit var viewModel: HomeListViewModel
    private val fragmentNavigationHelper: FragmentNavigationHelper = FragmentNavigationHelperImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        prepareViewModel()
        return inflater.inflate(R.layout.home_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
        viewModel.startProcesses()
    }

    override fun prepareViewModel() {
        fragmentManager?.let {
            fragmentNavigationHelper.configHelper(it, R.id.mainContainer)
        }
        viewModel = HomeListViewModelImpl(HomeListNavigatorImpl(fragmentNavigationHelper))
    }

    override fun prepareView() {

    }
}
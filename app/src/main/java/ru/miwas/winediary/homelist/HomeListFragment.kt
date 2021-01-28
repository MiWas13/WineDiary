package ru.miwas.winediary.homelist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_list_fragment.*
import kotlinx.android.synthetic.main.home_list_fragment.addButton
import ru.miwas.winediary.R
import ru.miwas.winediary.base.BaseFragment
import ru.miwas.winediary.di.DaggerDI
import ru.miwas.winediary.homelist.model.WineItem
import ru.miwas.winediary.homelist.HomeListViewModel.Event.AddClicked
import ru.miwas.winediary.homelist.HomeListViewModel.Event.WineClicked
import ru.miwas.winediary.homelist.di.component.DaggerHomeListComponent
import ru.miwas.winediary.homelist.di.module.HomeListFragmentModule
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import javax.inject.Inject

class HomeListFragment : BaseFragment() {

    @Inject
    lateinit var fragmentNavigationHelper: FragmentNavigationHelper

    @Inject
    lateinit var viewModel: HomeListViewModel

    private val homeItemClickListener: HomeItemClickListener = object : HomeItemClickListener {
        override fun onClick(id: Long) {
            viewModel.dispatchEvent(WineClicked(id))
        }
    }

    private val homeListAdapter = HomeListAdapter(homeItemClickListener)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerHomeListComponent
            .builder()
            .homeListFragmentModule(HomeListFragmentModule(this))
            .appComponent(DaggerDI.appComponent)
            .build()
            .inject(this)
    }

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
        observeViewModel()
        viewModel.startProcesses()
    }

    override fun prepareViewModel() {
        fragmentManager?.let {
            fragmentNavigationHelper.configHelper(it, R.id.mainContainer)
        }
    }

    override fun prepareView() {
        addButton.setOnClickListener {
            viewModel.dispatchEvent(AddClicked)
        }

        homeListRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeListAdapter
        }
    }

    override fun observeViewModel() {
        viewModel.wineItems.observe(
            viewLifecycleOwner,
            Observer {
                homeListAdapter.setItems(it)
            }
        )
    }

    fun generateFakeItems() = arrayListOf(
        WineItem(
            id = 0,
            name = "Каберне la France",
            rateTotal = 2
        ),
        WineItem(
            id = 1,
            name = "Шато Тамань",
            rateTotal = 4
        ),
        WineItem(
            id = 2,
            name = "Frederic Monplaisir Bordeaux",
            rateTotal = 5
        )
    )
}
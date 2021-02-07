package ru.miwas.winediary.homelist

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.miwas.winediary.R
import ru.miwas.winediary.core.base.BaseFragment
import ru.miwas.winediary.core.viewbinding.viewBinding
import ru.miwas.winediary.databinding.HomeListFragmentBinding
import ru.miwas.winediary.di.DaggerDI
import ru.miwas.winediary.homelist.model.WineItem
import ru.miwas.winediary.homelist.HomeListViewModel.Event.AddClicked
import ru.miwas.winediary.homelist.HomeListViewModel.Event.WineClicked
import ru.miwas.winediary.homelist.di.component.DaggerHomeListComponent
import ru.miwas.winediary.homelist.di.module.HomeListFragmentModule
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import javax.inject.Inject

class HomeListFragment : BaseFragment(R.layout.home_list_fragment) {

    @Inject
    lateinit var fragmentNavigationHelper: FragmentNavigationHelper

    @Inject
    lateinit var viewModel: HomeListViewModel

    private val binding by viewBinding(HomeListFragmentBinding::bind)

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = resources.getColor(R.color.dirtyWhite, null)
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

    override fun prepareView() {
        binding.addButton.setOnClickListener {
            viewModel.dispatchEvent(AddClicked)
        }

        binding.homeListRecycler.apply {
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
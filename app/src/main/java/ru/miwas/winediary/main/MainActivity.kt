package ru.miwas.winediary.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.miwas.winediary.R
import ru.miwas.winediary.main.navigation.MainNavigatorImpl
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.navigationcore.FragmentNavigationHelperImpl


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentNavigationHelper: FragmentNavigationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = resources.getColor(R.color.dirtyWhite, null)

        fragmentNavigationHelper = FragmentNavigationHelperImpl()
        fragmentNavigationHelper.configHelper(
            supportFragmentManager,
            R.id.mainContainer
        )

        prepareViewModel()
        viewModel.startProcesses()
    }

    private fun prepareViewModel() {
        viewModel = MainViewModelImpl(MainNavigatorImpl(fragmentNavigationHelper))
    }
}
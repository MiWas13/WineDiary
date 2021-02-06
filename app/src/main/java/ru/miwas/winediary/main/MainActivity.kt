package ru.miwas.winediary.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.miwas.winediary.R
import ru.miwas.winediary.di.DaggerDI
import ru.miwas.winediary.main.di.component.DaggerMainComponent
import ru.miwas.winediary.main.di.module.MainActivityModule
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentNavigationHelper: FragmentNavigationHelper

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = resources.getColor(R.color.dirtyWhite, null)

        DaggerMainComponent
            .builder()
            .mainActivityModule(MainActivityModule(this))
            .appComponent(DaggerDI.appComponent)
            .build()
            .inject(this)

        fragmentNavigationHelper.configHelper(
            supportFragmentManager,
            R.id.mainContainer
        )

        viewModel.startProcesses()
    }
}
package ru.miwas.winediary.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.miwas.winediary.R
import ru.miwas.winediary.core.viewbinding.viewBinding
import ru.miwas.winediary.databinding.ActivityMainBinding
import ru.miwas.winediary.di.DaggerDI
import ru.miwas.winediary.main.di.component.DaggerMainComponent
import ru.miwas.winediary.main.di.module.MainActivityModule
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var fragmentNavigationHelper: FragmentNavigationHelper

    @Inject
    lateinit var viewModel: MainViewModel

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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
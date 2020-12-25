package ru.miwas.winediary.base
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun prepareView()

    abstract fun prepareViewModel()

    abstract fun observeViewModel()
}
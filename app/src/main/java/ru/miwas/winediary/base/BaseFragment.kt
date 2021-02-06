package ru.miwas.winediary.base
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun prepareView()

    abstract fun prepareViewModel()

    abstract fun observeViewModel()

    private var backPressedCallback: OnBackPressedCallback? = null

    private fun addBackPressedCallback(callback: OnBackPressedCallback) {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
        backPressedCallback = callback
    }

    protected fun addBackPressedCallback(callback: () -> Unit) {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                callback()
            }
        }
        addBackPressedCallback(onBackPressedCallback)
    }
}